package com.example.covid_19info.data.api;

import com.example.covid_19info.data.model.Jhucsse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.covid_19info.data.api.APIConstants.ALL;
import static com.example.covid_19info.data.api.APIConstants.COUNTRIES;
import static com.example.covid_19info.data.api.APIConstants.JHUCSSE;


/**
 * API retrofit collection for GET calls
 */
public interface RetroGetCalls {

    /**
     * Get JHU CSSE Data. This includes confirmed cases, deaths, recovered, and coordinates.
     */

    @GET(JHUCSSE)
    Call<ArrayList<Jhucsse>> getAllJhucsseData();


    /**
     * Get global stats: cases, deaths, recovered, time last updated, and active cases
     */

    @GET(ALL)
    Call<ArrayList<Jhucsse>> getGlobalData();


    /**
     * Returns a JSON array with an element for each country that has stats available.
     * This includes iso codes, lat/long, a link to the country flag, cases, new cases, deaths,
     * new deaths, recovered, active cases, critical cases, and cases/deaths per one million people
     */

    @GET(COUNTRIES)
    Call<ArrayList<Jhucsse>> getAllGlobalData();

}