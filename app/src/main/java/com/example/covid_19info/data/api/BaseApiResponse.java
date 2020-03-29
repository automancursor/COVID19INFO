package com.example.covid_19info.data.api;

public interface BaseApiResponse<T> {

    /**
     * the success response of the What_Next API call and facilitated by output data as required
     *
     * @param data    the response data object of the API that's serialized to the ADr. model
     */
    void onSuccess(T data);

    /**
     * Called the error message for any API call failure
     *
     * @param errorMessage is the error message, reason of the API call failure
     */
    void onError(String errorMessage);

}