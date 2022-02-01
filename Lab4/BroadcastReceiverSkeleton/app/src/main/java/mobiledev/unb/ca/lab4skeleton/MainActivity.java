package mobiledev.unb.ca.lab4skeleton;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final String TIME_STAMP_FORMAT = "yyyyMMdd_HHmmss";
    private static final int INTERVAL_SIXTY_SECONDS = 60 * 1000;

    // Attributes for working with an alarm
    private AlarmManager alarmManager;
    private PendingIntent alarmReceiverIntent;

    // Attributes for storing the file photo path
    private String currentPhotoPath;
    private String imageFileName;

    // Activity listeners
    private ActivityResultLauncher<Intent> cameraActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button cameraButton = findViewById(R.id.button);
        cameraButton.setOnClickListener(view -> dispatchTakePhotoIntent());

        // Register the activity listener
        setCameraActivityResultLauncher();

        // Set the broadcast receiver alarm values
        initAlarmValues();
        // Set the battery filter intents
        setBatteryIntentFilters();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the battery receivers to avoid memory leaks
        removeBatteryIntentFilters();
    }

    // Private Helper Methods
    private void setCameraActivityResultLauncher() {
        cameraActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        galleryAddPic();
                    }
                });
    }

    private void initAlarmValues() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        final Intent setAlarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        alarmReceiverIntent = PendingIntent.getBroadcast(MainActivity.this,
                0,
                setAlarmIntent,
                PendingIntent.FLAG_IMMUTABLE);

        // Start the alarm
        startAlarm();
    }

    private void startAlarm() {
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                SystemClock.elapsedRealtime() + INTERVAL_SIXTY_SECONDS,
                INTERVAL_SIXTY_SECONDS,
                alarmReceiverIntent);

        Log.i(TAG, "Alarm Started");
    }

    private void cancelAlarm() {
        alarmManager.cancel(alarmReceiverIntent);
        Log.i(TAG, "Alarm Cancelled");
    }

    // Battery check methods
    private final BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String intentAction = intent.getAction();
            if (Intent.ACTION_BATTERY_OKAY.equals(intentAction)) {
                startAlarm();
                Toast.makeText(MainActivity.this,
                        "Battery level good; starting the alarm",
                        Toast.LENGTH_SHORT).show();
            }

            if (Intent.ACTION_BATTERY_LOW.equals(intentAction)) {
                cancelAlarm();
                Toast.makeText(MainActivity.this,
                        "Battery level low; cancelling the alarm",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    // Power Check Methods
    private final BroadcastReceiver powerInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String intentAction = intent.getAction();
            if (Intent.ACTION_POWER_CONNECTED.equals(intentAction)) {
                startAlarm();
                Toast.makeText(MainActivity.this,
                        "Device plugged in; starting the alarm",
                        Toast.LENGTH_SHORT).show();
            }

            if (Intent.ACTION_POWER_DISCONNECTED.equals(intentAction)) {
                cancelAlarm();
                Toast.makeText(MainActivity.this,
                        "Device unplugged; cancelling the alarm",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void setBatteryIntentFilters() {
        final IntentFilter batteryIntentFilter = new IntentFilter();
        batteryIntentFilter.addAction(Intent.ACTION_BATTERY_OKAY);
        batteryIntentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        registerReceiver(batteryInfoReceiver, batteryIntentFilter);

        final IntentFilter powerIntentFilter = new IntentFilter();
        powerIntentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        powerIntentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(powerInfoReceiver, powerIntentFilter);
    }

    private void removeBatteryIntentFilters() {
        unregisterReceiver(batteryInfoReceiver);
        unregisterReceiver(powerInfoReceiver);
    }

    // Camera methods
    private void dispatchTakePhotoIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there is a camera activity to handle the intent
        try {
            // Set the File object used to save the photo
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e(TAG, "Exception found when creating the photo save file");
            }

            // Take the picture if the File object was created successfully
            if (null != photoFile) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "mobiledev.unb.ca.lab4skeleton.provider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                // Calling this method allows us to capture the return code
                cameraActivityResultLauncher.launch(takePictureIntent);
            }
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "Unable to load activity", e);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat(TIME_STAMP_FORMAT, Locale.getDefault()).format(new Date());
        imageFileName = "IMG_" + timeStamp + "_";

        File storageDir =  getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",   // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Log.d(TAG, "Saving image to the gallery");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Android 10 and above
            mediaStoreAddPicToGallery();
        } else {
            // Pre Android 10
            mediaScannerAddPicToGallery();
        }
        Log.i(TAG, "Image saved!");
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void mediaStoreAddPicToGallery() {
        String name = imageFileName;
        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name + ".jpg");
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);

        ContentResolver resolver = getContentResolver();
        Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        try (OutputStream fos = resolver.openOutputStream(Objects.requireNonNull(imageUri))) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            //Objects.requireNonNull(fos).close();
        } catch (IOException e){
            Log.e(TAG,"Error saving the file ", e);
        }
    }

    private void mediaScannerAddPicToGallery() {
        File file = new File(currentPhotoPath);
        MediaScannerConnection.scanFile(this,
                new String[]{file.toString()},
                new String[]{file.getName()},
                null);
    }
}
