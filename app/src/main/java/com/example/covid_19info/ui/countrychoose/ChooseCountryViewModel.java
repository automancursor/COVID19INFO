package com.example.covid_19info.ui.countrychoose;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.covid_19info.data.model.Country;
import com.example.covid_19info.utils.AppUtils;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.covid_19info.utils.Constants.COUNTRY;

public class ChooseCountryViewModel extends AndroidViewModel {

    private Context context = getApplication().getApplicationContext();

    public ChooseCountryViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * retrieve the country list from local
     *
     * @return
     */
    List<Country> getCountries() {
        return AppUtils.getCountries(context);
    }

    /**
     * save the country iso3
     *
     * @param country country name
     */
    void saveCountry(String country) {
        AppUtils.saveCountry(context, country);
    }

    /**
     * check if the country is03 is set in the local storage
     *
     * @return true if saved, false if not
     */
    boolean isCountrySelected() {
        SharedPreferences preferences = context.getSharedPreferences(COUNTRY, MODE_PRIVATE);
        return !"".equalsIgnoreCase(preferences.getString(COUNTRY, ""));
    }
}
