package mobiledev.unb.ca.labexam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // TODO
        //  Get the intent that started this activity along with the extras added to it

        // TODO
        //  Set the details for the number, year, and dates text views

        // TODO
        //  Set an onClickListener such that when this button is clicked, an implicit intent is started
        //  to open the wikipedia URL in a web browser. Be sure to check that there is
        //  an application installed that can handle this intent before starting it.
        //  If the intent can't be started, show a toast indicating this.
        // Hints:
        // https://developer.android.com/reference/android/content/Intent.html#resolveActivity(android.content.pm.PackageManager)
        // https://developer.android.com/guide/components/intents-common.html#Browser
        // https://developer.android.com/reference/android/net/Uri.html#parse(java.lang.String)

        // TODO
        //  Set the title of the action bar to be the host city name
    }
}
