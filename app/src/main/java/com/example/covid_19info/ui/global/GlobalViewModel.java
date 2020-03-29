package com.example.covid_19info.ui.global;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covid_19info.data.api.APICall;
import com.example.covid_19info.data.api.BaseApiResponse;
import com.example.covid_19info.data.model.GlobalData;
import com.example.covid_19info.data.model.OverallData;

import java.util.ArrayList;

public class GlobalViewModel extends ViewModel {

    private APICall apiCall;
    private MutableLiveData<OverallData> data;
    private MutableLiveData<ArrayList<GlobalData>> allData;

    public GlobalViewModel() {
        apiCall = new APICall();
        data = new MutableLiveData<>();

        apiCall.getGlobalData(new BaseApiResponse<OverallData>() {
            @Override
            public void onSuccess(OverallData res) {
                data.setValue(res);
            }

            @Override
            public void onError(String errorMessage) {
                //show error
            }
        });

        apiCall.getAllGlobalData(new BaseApiResponse<ArrayList<GlobalData>>() {
            @Override
            public void onSuccess(ArrayList<GlobalData> res) {
                allData.setValue(res);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    public LiveData<OverallData> getData() {
        return data;
    }

    public LiveData<ArrayList<GlobalData>> getAllData() {
        return allData;
    }

}