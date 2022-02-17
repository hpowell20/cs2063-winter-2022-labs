package mobiledev.unb.ca.recyclerviewlab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mobiledev.unb.ca.recyclerviewlab.model.Course;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO 1
        //  Get the ArrayList of Courses from the JsonUtils class
        //  (Ideally we would do this loading off of the main thread. We'll get to that
        //  in the next lab. Today we're focusing on displaying scrolling lists.)

        // TODO 2
        //  Get a reference to the RecyclerView by creating an instance of MyAdapter
        //  using the following parameters:
        //    - The ArrayList of courses from above.
        //    - A reference to the parent Activity (in this case MainActivity)
    }
}
