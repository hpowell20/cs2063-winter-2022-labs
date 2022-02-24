package mobiledev.unb.ca.threadinglab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mobiledev.unb.ca.threadinglab.model.GeoData;

public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final List<GeoData> mValues;
    private final AppCompatActivity parentActivity;
    private final boolean isTwoPane;

    public SimpleItemRecyclerViewAdapter(List<GeoData> data, AppCompatActivity parentActivity, boolean isTwoPane) {
        mValues = data;
        this.parentActivity = parentActivity;
        this.isTwoPane = isTwoPane;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.geodata_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mGeoData = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getTitle());

        holder.mView.setOnClickListener(v -> {
            /*
               Setting the data to be sent to the Detail portion of the template.
               Here, we send the title, longitude, and latitude of the Earthquake
               that was clicked in the RecyclerView. The Detail Activity/Fragment
               will then display this information. Condition check is whether we
               are twoPane on a Tablet, which varies how we pass arguments to the
               participating activity/fragment.
             */
            String title = holder.mGeoData.getTitle();
            String lng = holder.mGeoData.getLongitude();
            String lat = holder.mGeoData.getLatitude();

            if (isTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(GeoDataDetailFragment.TITLE, title);
                arguments.putString(GeoDataDetailFragment.LNG, lng);
                arguments.putString(GeoDataDetailFragment.LAT, lat);
                GeoDataDetailFragment fragment = new GeoDataDetailFragment();
                fragment.setArguments(arguments);
                parentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.geodata_detail_container, fragment)
                        .commit();
            } else {
                // TODO
                //  Create an Intent to start the parentActivity class.
                //  You'll need to add some extras to this intent. Look at that class, and the
                //  example Fragment transaction for the two pane case above, to
                //  figure out what you need to add.
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public GeoData mGeoData;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
            mContentView = view.findViewById(R.id.content);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}