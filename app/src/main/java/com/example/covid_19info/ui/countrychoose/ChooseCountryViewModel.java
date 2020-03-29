package com.example.covid_19info.ui.countrychoose;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

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

public class ChooseCountryViewModel extends AndroidViewModel {

    private static final String COUNTRY = "country";
    private Context context = getApplication().getApplicationContext();

    public ChooseCountryViewModel(@NonNull Application application) {
        super(application);
    }


    public List<Country> getCountries() {
        String countries = loadJSONFromAsset();
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


    public String loadJSONFromAsset() {
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

    public void saveCountry(String country, boolean isCountrySelected) {

        String[] fullName = country.split("-");
        String iso = fullName[1];
        String iso3 = iso.replaceAll("\\p{P}", "").trim();

        SharedPreferences preferences = context.getSharedPreferences(COUNTRY, MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();

        edit.putString(COUNTRY, iso3);
        edit.commit();

    }

    public boolean isCountrySelected() {
        SharedPreferences preferences = context.getSharedPreferences(COUNTRY, MODE_PRIVATE);
        return !"".equalsIgnoreCase(preferences.getString(COUNTRY, ""));
    }
}
