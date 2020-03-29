package com.example.covid_19info.ui.setting;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

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

public class SettingViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private Context context = getApplication().getApplicationContext();
    private static final String COUNTRY = "country";

    public SettingViewModel(@NonNull Application application) {
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


    public String getCurrentCountry() {
        SharedPreferences preferences = context.getSharedPreferences(COUNTRY, MODE_PRIVATE);
        return preferences.getString(COUNTRY, "");
    }

 /*   public SettingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is setting fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }*/
}