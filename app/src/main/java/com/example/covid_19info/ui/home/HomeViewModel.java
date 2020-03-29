package com.example.covid_19info.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covid_19info.data.api.APICall;
import com.example.covid_19info.data.api.BaseApiResponse;
import com.example.covid_19info.data.model.OverallData;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private APICall apiCall;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        apiCall = new APICall();

        apiCall.getGlobalData(new BaseApiResponse<OverallData>() {
            @Override
            public void onSuccess(OverallData data) {
                mText.setValue(String.valueOf(data.getCases()));
            }

            @Override
            public void onError(String errorMessage) {

                mText.setValue(errorMessage);
            }
        });
    }

    LiveData<String> getText() {
        return mText;
    }
}