package mobiledev.unb.ca.labexam.util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import mobiledev.unb.ca.labexam.model.GamesInfo;

public class JsonUtils {
    private static final String INPUT_JSON_FILE = "winter_games.json";

    private static final String KEY_HOST_CITIES = "host_cities";
    private static final String KEY_YEAR = "year";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_HOST_CITY = "host_city";
    private static final String KEY_DATES = "dates";
    private static final String KEY_WIKIPEDIA_LINK = "wikipedia_link";

    private ArrayList<GamesInfo> hostCitiesArray;

    // Initializer to read our data source (JSON file) into an array of host city objects
    public JsonUtils(Context context) {
        processJSON(context);
    }

    private void processJSON(Context context) {
        hostCitiesArray = new ArrayList<>();

        try {
            // Create a JSON Object from file contents String
            JSONObject jsonObject = new JSONObject(loadJSONFromAssets(context));

            // Create a JSON Array from the JSON Object
            // This array is the "courses" array mentioned in the lab write-up
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_HOST_CITIES);

            for (int i=0; i < jsonArray.length(); i++) {
                // Create a JSON Object from individual JSON Array element
                JSONObject elementObject = jsonArray.getJSONObject(i);

                // Get data from individual JSON Object
                GamesInfo city = new GamesInfo.Builder(elementObject.getString(KEY_YEAR),
                        elementObject.getString(KEY_NUMBER),
                        elementObject.getString(KEY_HOST_CITY),
                        elementObject.getString(KEY_DATES),
                        elementObject.getString(KEY_WIKIPEDIA_LINK))
                        .build();

                // Add new GamesInfo to courses ArrayList
                hostCitiesArray.add(city);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAssets(Context context) {
        try (InputStream is = context.getAssets().open(INPUT_JSON_FILE)){
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public ArrayList<GamesInfo> getHostCities() {
        return hostCitiesArray;
    }
}
