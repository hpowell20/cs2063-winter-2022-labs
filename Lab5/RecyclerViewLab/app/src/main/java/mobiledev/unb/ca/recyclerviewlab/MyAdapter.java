package mobiledev.unb.ca.recyclerviewlab;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mobiledev.unb.ca.recyclerviewlab.model.Course;

// The RecyclerView.Adapter class provides a layer of abstraction between the
// RecyclerView's LayoutManager and the underlying data that is being displayed,
// in this case the ArrayList of Course objects.
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final static String TAG = "My Adapter";
    private final ArrayList<Course> mDataset;
    private final Activity parentActivity;

    public MyAdapter(ArrayList<Course> myDataset, Activity parentActivity) {
        mDataset = myDataset;
        this.parentActivity = parentActivity;
    }

    // ViewHolder represents an individual item to display. In this case
    // it will just be a single TextView (displaying the title of a course)
    // but RecyclerView gives us the flexibility to do more complex things
    // (e.g., display an image and some text).
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // The inflate method of the LayoutInflater class can be used to obtain the
    // View object corresponding to an XML layout resource file. Here
    // onCreateViewHolder inflates the TextView corresponding to item_layout.xml
    // and uses it to instantiate a ViewHolder.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(v);
    }

    // onBindViewHolder binds a ViewHolder to the data at the specified
    // position in mDataset
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO 3
        //  Get the Course at index position in mDataSet
        //  (Hint: you might need to declare this variable as final.)

        // TODO 4
        //  Set the TextView in the ViewHolder (holder) to be the title for this Course

        // TODO 5
        //  Set the onClickListener for the TextView in the ViewHolder (holder) such
        //  that when it is clicked, it creates an explicit intent to launch DetailActivity
        //  HINT: You will need to put two extra pieces of information in this intent:
        //      The Course title and it's description
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}