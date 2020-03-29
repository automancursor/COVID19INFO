package com.example.covid_19info.data.api;

import androidx.annotation.NonNull;

import com.example.covid_19info.data.model.GlobalData;
import com.example.covid_19info.data.model.Jhucsse;
import com.example.covid_19info.data.model.OverallData;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.covid_19info.data.api.APIConstants.SOMETHING_WENT_WRONG;
import static com.example.covid_19info.data.api.APIConstants.PLEASE_TRY_AGAIN;

/**
 * All the details page service calls
 */
class APICall {

    private RetroGetCalls getCalls;

    APICall() {
        getCalls = new APIServiceConfig(APIConstants.BASE_URL).createService(RetroGetCalls.class);
    }

    /**
     * Get all the data of provided from Jhu csse
     *
     * @param responseListener response listener
     */
    void getAllJhucsseData(BaseApiResponse<ArrayList<Jhucsse>> responseListener) {
        getCalls.getAllJhucsseData()
                .enqueue(new Callback<ArrayList<Jhucsse>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<Jhucsse>> call,
                                           @NonNull Response<ArrayList<Jhucsse>> response) {

                        if (response.isSuccessful())

                            responseListener.onSuccess(
                                    response.body());
                        else
                            responseListener.onError(SOMETHING_WENT_WRONG);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<Jhucsse>> call,
                                          @NonNull Throwable t) {
                        responseListener.onError(PLEASE_TRY_AGAIN);
                    }
                });
    }

    /**
     * Get all the global data
     *
     * @param responseListener response listener
     */
    void getAllGlobalData(BaseApiResponse<ArrayList<GlobalData>> responseListener) {
        getCalls.getAllGlobalData()
                .enqueue(new Callback<ArrayList<GlobalData>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<GlobalData>> call,
                                           @NonNull Response<ArrayList<GlobalData>> response) {

                        if (response.isSuccessful())

                            responseListener.onSuccess(
                                    response.body());
                        else
                            responseListener.onError(SOMETHING_WENT_WRONG);
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<GlobalData>> call,
                                          @NonNull Throwable t) {
                        responseListener.onError(PLEASE_TRY_AGAIN);
                    }
                });
    }

    /**
     * get all the cumulative data from the globe
     *
     * @param responseListener response listener
     */
    void getGlobalData(BaseApiResponse<OverallData> responseListener) {
        getCalls.getGlobalData()
                .enqueue(new Callback<OverallData>() {
                    @Override
                    public void onResponse(@NonNull Call<OverallData> call,
                                           @NonNull Response<OverallData> response) {

                        if (response.isSuccessful())

                            responseListener.onSuccess(
                                    response.body());
                        else
                            responseListener.onError(SOMETHING_WENT_WRONG);
                    }

                    @Override
                    public void onFailure(@NonNull Call<OverallData> call,
                                          @NonNull Throwable t) {
                        responseListener.onError(PLEASE_TRY_AGAIN);
                    }
                });
    }

    /**
     * Get the data as per the country name
     *
     * @param responseListener response listener
     * @param countryISO3      iso3 code of the country
     */
    void getCountryData(BaseApiResponse<GlobalData> responseListener, String countryISO3) {
        getCalls.getCountryData(countryISO3)
                .enqueue(new Callback<GlobalData>() {
                    @Override
                    public void onResponse(@NonNull Call<GlobalData> call,
                                           @NonNull Response<GlobalData> response) {

                        if (response.isSuccessful())

                            responseListener.onSuccess(
                                    response.body());
                        else
                            responseListener.onError(SOMETHING_WENT_WRONG);
                    }

                    @Override
                    public void onFailure(@NonNull Call<GlobalData> call,
                                          @NonNull Throwable t) {
                        responseListener.onError(PLEASE_TRY_AGAIN);
                    }
                });
    }

}
