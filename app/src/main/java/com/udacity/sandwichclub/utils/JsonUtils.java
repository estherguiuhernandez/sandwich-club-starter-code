package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class JsonUtils {

    /** Tag for the log messages */
    public static final String LOG_TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try{
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject nameJson = sandwichJson.getJSONObject("name");
            String mainName = nameJson.getString("mainName");
            JSONArray alsoKnownAs = nameJson.getJSONArray("alsoKnownAs");
            String placeOfOrigin = sandwichJson.getString("placeOfOrigin");
            String description = sandwichJson.getString("description");
            String image = sandwichJson.getString("image");
            JSONArray ingredients = sandwichJson.getJSONArray("ingredients");

            return new Sandwich(mainName, Collections.<String>emptyList(), placeOfOrigin, description, image, Collections.<String> emptyList());

        }catch(JSONException e){
            Log.e(LOG_TAG, "Problem parsing the sandwitch results", e);
        }
        return null;

    }
}
