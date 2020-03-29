package com.example.covid_19info.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covid_19info.data.api.APICall;
import com.example.covid_19info.data.api.BaseApiResponse;
import com.example.covid_19info.data.model.OverallData;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<OverallData> data;
    private APICall apiCall;

    public HomeViewModel() {
        data = new MutableLiveData<>();
        apiCall = new APICall();

        apiCall.getGlobalData(new BaseApiResponse<OverallData>() {
            @Override
            public void onSuccess(OverallData response) {
                data.setValue(response);
            }

            @Override
            public void onError(String errorMessage) {
                //show error
            }
        });
    }

    LiveData<OverallData> getData() {
        return data;
    }
}