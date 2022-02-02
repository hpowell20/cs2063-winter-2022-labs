package mobiledev.unb.ca.lab4skeleton;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.util.Log;
import android.widget.Button;

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
                        "mobiledev.unb.ca.lab3intents.provider",
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
        } catch (IOException e){
            Log.e(TAG,"Error saving the file ", e);
        }
    }

    private void mediaScannerAddPicToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}
