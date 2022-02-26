package mobiledev.unb.ca.threadinglab;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import mobiledev.unb.ca.threadinglab.model.GeoData;

public class DownloaderTask {
    private static final int DOWNLOAD_TIME = 4;      // Download time simulation

    private final Context appContext;

    private final GeoDataListActivity activity;
    private Button refreshButton;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public DownloaderTask(GeoDataListActivity activity) {
        this.activity = activity;
        appContext = activity.getApplicationContext();
    }

    public DownloaderTask setRefreshButton(Button refreshButton) {
        this.refreshButton = refreshButton;
        return this;
    }

    public DownloaderTask setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
        return this;
    }

    public DownloaderTask setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public void execute() {
        // TODO
        //  Disable the button so it can't be clicked again once a download has been started
        //  Hint: Button is subclass of TextView. Read this document to see how to disable it.
        //  http://developer.android.com/reference/android/widget/TextView.html

        // TODO
        //  Set the progress bar's maximum to be DOWNLOAD_TIME, its initial progress to be
        //  0, and also make sure it's visible.
        //  Hint: Read the documentation on ProgressBar
        //  http://developer.android.com/reference/android/widget/ProgressBar.html

        // Perform background call to read the information from the URL
        Executors.newSingleThreadExecutor().execute(() -> {
            Handler mainHandler = new Handler(Looper.getMainLooper());

            // TODO
            //  Create an instance of JsonUtils and get the data from it,
            //  store it in a local variable

            // Simulating long-running operation
            for (int i = 1; i < DOWNLOAD_TIME; i++) {
                sleep();
                // TODO
                //  Update the progress bar using values
            }

            // TODO
            //  Using the updateDisplay method update the UI with the results
        });
    }

    private void sleep() {
        try {
            int mDelay = 500;
            Thread.sleep(mDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateDisplay(ArrayList<GeoData> mGeoDataList) {
        // TODO
        //  With the download completed, enable the button again

        // TODO
        //  Reset the progress bar, and make it disappear

        // TODO
        //  Setup the RecyclerView

        // TODO
        //  Create a Toast indicating that the download is complete
    }

    private void setupRecyclerView(List<GeoData> mGeoDataList) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(mGeoDataList, activity, activity.isTwoPane()));
    }
}
