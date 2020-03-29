package com.example.covid_19info.data.model;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(value = Parcel.Serialization.BEAN)
public class OverallData {

    @SerializedName("cases")
    private int cases;
    @SerializedName("deaths")
    private int deaths;
    @SerializedName("recovered")
    private int recovered;
    @SerializedName("updated")
    private long updated;
    @SerializedName("active")
    private int active;

    public int getCases() {
        return cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public long getUpdated() {
        return updated;
    }

    public int getActive() {
        return active;
    }
}
