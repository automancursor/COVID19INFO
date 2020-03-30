package com.example.covid_19info.ui.setting;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.covid_19info.data.model.Country;
import com.example.covid_19info.utils.AppUtils;
import com.example.covid_19info.utils.Constants;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SettingViewModel extends AndroidViewModel {

    private Context context = getApplication().getApplicationContext();

    public SettingViewModel(@NonNull Application application) {
        super(application);
    }

    List<Country> getCountries() {
        return AppUtils.getCountries(context);
    }

    String getCurrentCountry() {
        SharedPreferences preferences = context.getSharedPreferences(Constants.COUNTRY, MODE_PRIVATE);
        return preferences.getString(Constants.FULL_COUNTRY_NAME, "");
    }

}