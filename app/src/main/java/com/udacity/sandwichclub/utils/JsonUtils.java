package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {

        List<String> alsoKnownAs = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject nameJson = sandwichJson.getJSONObject("name");
            String mainName = nameJson.getString("mainName");
            JSONArray alsoKnownAsJson = nameJson.getJSONArray("alsoKnownAs");
            String placeOfOrigin = sandwichJson.getString("placeOfOrigin");
            String description = sandwichJson.getString("description");
            String image = sandwichJson.getString("image");
            JSONArray ingredientsJson = sandwichJson.getJSONArray("ingredients");


            if (alsoKnownAsJson != null) {
                for (int i = 0; i < alsoKnownAsJson.length(); i++) {
                    alsoKnownAs.add(alsoKnownAsJson.get(i).toString());
                }
            }

            if (ingredientsJson != null) {
                for (int i = 0; i < ingredientsJson.length(); i++) {
                    ingredients.add(ingredientsJson.get(i).toString());
                }
            }
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the sandwitch results", e);
        }
        return null;

    }
}
