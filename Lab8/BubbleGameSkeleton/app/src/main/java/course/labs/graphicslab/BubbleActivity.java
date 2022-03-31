package course.labs.graphicslab;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class BubbleActivity extends Activity {
    private static final String TAG = "BubbleActivity";
    private static final int SOUND_POOL_MAX_STREAMS = 10;
    private static final int STREAM_TYPE = AudioManager.STREAM_MUSIC;

    // The Main view
    private RelativeLayout mFrame;

    // Bubble image's bitmap
    private Bitmap bitmap;

    // Display dimensions
    private int displayWidth, displayHeight;

    // Gesture Detector
    private GestureDetector gestureDetector;

    // A TextView to hold the current number of bubbles
    private TextView bubbleCountTextView;

    // Sound variables

    // SoundPool
    private SoundPool soundPool;
    // ID for the bubble popping sound
    private int soundID;
    // Audio volume
    private float streamVolume;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        // Set up user interface
        mFrame = findViewById(R.id.frame);
        bubbleCountTextView = findViewById(R.id.bubbles_text);

        // Initialize the number of bubbles
        updateNumBubblesTextView();

        // Load basic bubble Bitmap
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b64);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setStreamVolume();

        // TODO
        //  Make a new SoundPool, allowing up to 10 streams
        //  Store this as soundPool

        // TODO
        //  Set a SoundPool OnLoadCompletedListener that calls setupGestureDetector()

        // TODO
        //  Load the sound from res/raw/bubble_pop.wav
        //  Store this as soundID
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // Get the size of the display so this View knows where borders are
            displayWidth = mFrame.getWidth();
            displayHeight = mFrame.getHeight();
        }
    }

    // Setup the stream volume
    private void setStreamVolume() {
        // Manage bubble popping sound
        // Use AudioManager.STREAM_MUSIC as stream type

        // AudioManager audio settings for adjusting the volume
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Current volume Index of particular stream type
        float currentVolumeIndex = (float) audioManager.getStreamVolume(STREAM_TYPE);

        // Get the maximum volume index for a particular stream type
        float maxVolumeIndex = (float) audioManager.getStreamMaxVolume(STREAM_TYPE);

        // Set the volume between 0 --> 1
        streamVolume = currentVolumeIndex / maxVolumeIndex;

        setVolumeControlStream(STREAM_TYPE);
    }

    // Set up GestureDetector
    private void setupGestureDetector() {
        gestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent motionEvent) {
                        return true;
                    }
                    // If a fling gesture starts on a BubbleView then change the
                    // BubbleView's velocity based on x and y velocity from
                    // this gesture

                    @Override
                    public boolean onFling(MotionEvent event1, MotionEvent event2,
                                           float velocityX, float velocityY) {
                        // TODO
                        //  Implement onFling actions (See comment above for expected behaviour)
                        //  You can get all Views in mmFrame one at a time using the ViewGroup.getChildAt() method

                        return true;
                    }

                    // If a single tap intersects a BubbleView, then pop the BubbleView
                    // Otherwise, create a new BubbleView at the tap's location and add
                    // it to mFrame. Hint: Don't forget to start the movement of the
                    // BubbleView.
                    // Also update the number of bubbles displayed in the appropriate TextView

                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent event) {
                        // TODO - Implement onSingleTapConfirmed actions.
                        //  (See comment above for expected behaviour.)
                        //  You can get all Views in mFrame using the
                        //  ViewGroup.getChildCount() method

                        return true;
                    }
                });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO
        //  Delegate the touch to the gestureDetector

        // Remove this when you're done the above TODO
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        // TODO
        //  Release all SoundPool resources
    }

    // Method used to update the text view with the number of in view bubbles
    private void updateNumBubblesTextView() {
        String text = getString(R.string.txt_number_of_bubbles, mFrame.getChildCount());
        bubbleCountTextView.setText(text);
    }

    // BubbleView is a View that displays a bubble.
    // This class handles animating, drawing, and popping amongst other actions.
    // A new BubbleView is created for each bubble on the display
    public class BubbleView extends View {
        private static final int BITMAP_SIZE = 64;
        private static final int REFRESH_RATE = 40;
        private final Paint mPainter = new Paint();
        private ScheduledFuture<?> mMoverFuture;
        private int scaledBitmapSize;
        private Bitmap scaledBitmap;

        // location and direction of the bubble
        private float xPos, yPos, radius;

        // Speed of bubble
        private float dx, dy;

        // Rotation and speed of rotation of the bubble
        private long mRotate, mDRotate;

        BubbleView(Context context, float x, float y) {
            super(context);

            // Create a new random number generator to
            // randomize size, rotation, speed and direction
            Random r = new Random();

            // Creates the bubble bitmap for this BubbleView
            createScaledBitmap(r);

            // Radius of the Bitmap
            radius = scaledBitmapSize / 2;

            // Adjust position to center the bubble under user's finger
            xPos = x - radius;
            yPos = y - radius;

            // Set the BubbleView's speed and direction
            setSpeedAndDirection(r);

            // Set the BubbleView's rotation
            setRotation(r);

            mPainter.setAntiAlias(true);
        }

        private void setRotation(Random r) {
            // TODO
            //  Set rotation in range [1..5]
        }

        private void setSpeedAndDirection(Random r) {
            // TODO
            //  Set dx and dy to indicate movement direction and speed
            //  Limit speed in the x and y direction to [-3..3] pixels per movement
        }

        private void createScaledBitmap(Random r) {
            // TODO
            //  Set scaled bitmap size (scaledBitmapSize) in range [2..4] * BITMAP_SIZE

            // TODO
            //  Create the scaled bitmap (scaledBitmap) using size set above
        }

        // Start moving the BubbleView & updating the display
        private void startMovement() {
            // Creates a WorkerThread
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

            // Execute the run() in Worker Thread every REFRESH_RATE
            // milliseconds
            // Save reference to this job in mMoverFuture
            mMoverFuture = executor.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    // TODO
                    //  Implement movement logic.
                    //  Each time this method is run the BubbleView should
                    //  move one step. (Use moveWhileOnScreen() to do this.)
                    //  If the BubbleView exits the display, stop the BubbleView's
                    //  Worker Thread. (Use stopMovement() to do this.) Otherwise,
                    //  request that the BubbleView be redrawn.

                }
            }, 0, REFRESH_RATE, TimeUnit.MILLISECONDS);
        }

        // Returns true if the BubbleView intersects position (x,y)
        private synchronized boolean intersects(float x, float y) {
            float centerX = xPos + radius;
            float centerY = yPos + radius;

            // TODO
            //  Return true if the BubbleView intersects position (x,y)

            // Remove this when you're done the above todo
            return false;
        }

        // Cancel the Bubble's movement
        // Remove Bubble from mFrame
        // Play pop sound if the BubbleView was popped
        private void stopMovement(final boolean wasPopped) {
            if (null != mMoverFuture) {
                if (!mMoverFuture.isDone()) {
                    mMoverFuture.cancel(true);
                }

                // This work will be performed on the UI Thread
                mFrame.post(new Runnable() {
                    @Override
                    public void run() {
                        // TODO
                        //  Remove the BubbleView from mFrame

                        // TODO
                        //  Update the TextView displaying the number of bubbles
                        
                        // TODO
                        //  If the bubble was popped by user play the popping sound
                        //  HINT: Use the streamVolume for left and right volume parameters
                    }
                });
            }
        }

        // Change the Bubble's speed and direction
        private synchronized void deflect(float velocityX, float velocityY) {
            dx = velocityX / REFRESH_RATE;
            dy = velocityY / REFRESH_RATE;
        }

        // Draw the Bubble at its current location
        @Override
        protected synchronized void onDraw(Canvas canvas) {
            // TODO
            //  Save the canvas

            // TODO
            //  Increase the rotation of the original image by mDRotate

            // TODO
            //  Rotate the canvas by current rotation
            //  Hint - Rotate around the bubble's center, not its position

            // TODO
            //  Draw the bitmap at it's new location

            // TODO
            //  Restore the canvas
        }

        // Returns true if the BubbleView is still on the screen after the move
        // operation
        private synchronized boolean moveWhileOnScreen() {
            // TODO
            //  Move the BubbleView

            return isInView();
        }

        // Return true if the BubbleView is still on the screen after the move
        // operation
        private boolean isInView() {
            // TODO
            //  Return true if the BubbleView is still on the screen after
            //  the move operation

            // Remove this when you're done the above TODO
            return false;
        }
    }
}
