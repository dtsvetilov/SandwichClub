package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);

        String mainName = "";
        List<String> alsoKnownAs = new ArrayList<>();

        JSONObject nameJson = jsonObject.optJSONObject("name");
        if (nameJson != null) {
            mainName = nameJson.optString("mainName");
            alsoKnownAs = optStringList(nameJson, "alsoKnownAs");
        }

        String placeOfOrigin = jsonObject.optString("placeOfOrigin");
        String description = jsonObject.optString("description");
        String image = jsonObject.optString("image");

        List<String> ingredients = optStringList(jsonObject, "ingredients");

        Sandwich parsedRecord = new Sandwich(
                mainName,
                alsoKnownAs,
                placeOfOrigin,
                description,
                image,
                ingredients);

        return parsedRecord;
    }

    private static List<String> optStringList(JSONObject jsonObject, String name) throws JSONException {
        JSONArray jsonArray = jsonObject.optJSONArray(name);
        if (jsonArray == null)
            return new ArrayList<>();

        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String item = jsonArray.optString(i);
            resultList.add(item);
        }

        return resultList;
    }
}
