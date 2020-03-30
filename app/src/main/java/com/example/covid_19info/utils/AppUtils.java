package com.example.covid_19info.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Html;
import android.text.Spanned;

import com.example.covid_19info.data.model.Country;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class AppUtils {

    public static Spanned formatText(String title, int data) {
        return Html.fromHtml(String.format("%s<br><strong><big>%s</big></strong>", title, data), Html.FROM_HTML_MODE_COMPACT);
    }


    public static List<Country> getCountries(Context context) {
        String countries = loadJSONFromAsset(context);
        List<Country> countryList = new ArrayList<>();
        try {
            countryList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(countries);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Gson gson = new Gson();
                Country country = gson.fromJson(String.valueOf(jsonObject), Country.class);
                countryList.add(country);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return countryList;

    }


    private static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("countries.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static void saveCountry(Context context, String country) {
        String[] fullName = country.split("-");
        String iso = fullName[1];
        String iso3 = iso.replaceAll("\\p{P}", "").trim();

        SharedPreferences preferences = context.getSharedPreferences(Constants.COUNTRY, MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(Constants.COUNTRY, iso3);
        edit.putString(Constants.FULL_COUNTRY_NAME, country);
        edit.apply();
    }
}
