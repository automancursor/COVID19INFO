package com.example.covid_19info.ui.home;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.covid_19info.data.api.APICall;
import com.example.covid_19info.data.api.BaseApiResponse;
import com.example.covid_19info.data.model.GlobalData;
import com.example.covid_19info.utils.Constants;

import static android.content.Context.MODE_PRIVATE;

public class HomeViewModel extends AndroidViewModel {
    private APICall apiCall;
    private MutableLiveData<GlobalData> data;
    private Context context = getApplication().getApplicationContext();

    public HomeViewModel(@NonNull Application application) {
        super(application);

        String countryISO3 = context.getSharedPreferences(Constants.COUNTRY, MODE_PRIVATE).getString(Constants.COUNTRY, "");
        data = new MutableLiveData<>();
        apiCall = new APICall();

        apiCall.getCountryData(new BaseApiResponse<GlobalData>() {
            @Override
            public void onSuccess(GlobalData response) {
                data.setValue(response);
            }

            @Override
            public void onError(String errorMessage) {
                //show error
            }
        }, countryISO3);
    }

    /**
     *
     * @return the API retrieved global data of the country
     */
    LiveData<GlobalData> getData() {
        return data;
    }
}