package mobiledev.unb.ca.threadinglab;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * A fragment representing a single Course detail screen.
 * This fragment is either contained in a {@link GeoDataListActivity}
 * in two-pane mode (on tablets) or a {@link GeoDataDetailActivity}
 * on handsets.
 */
public class GeoDataDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String TITLE = "title";
    public static final String LNG = "item_longitude";
    public static final String LAT = "item_latitude";

    /**
     * The dummy content this fragment is presenting.
     */
    private String title;
    private String lng;
    private String lat;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GeoDataDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(LNG)) {
            title = arguments.getString(TITLE);
            lng = arguments.getString(LNG);
            lat = arguments.getString(LAT);

            Activity activity = this.getActivity();
            if (activity != null) {
                CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
                if (appBarLayout != null) {
                    appBarLayout.setTitle(getString(R.string.earthquake_details));
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.geodata_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (lat != null && lng != null) {
            String text = getString(R.string.detail_fragment_content, title, lng, lat);
            ((TextView) rootView.findViewById(R.id.geodata_detail)).setText(text);
        }

        return rootView;
    }
}
