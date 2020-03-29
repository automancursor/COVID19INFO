package com.example.covid_19info.data.model;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Date;

@Parcel(value = Parcel.Serialization.BEAN)
public class Jhucsse {

    @SerializedName("country")
    private String country;
    @SerializedName("province")
    private String province;
    @SerializedName("updatedAt")
    private Date updatedAt;
    @SerializedName("stats")
    private Stats fee;

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Stats getFee() {
        return fee;
    }

    private static class Stats {

        @SerializedName("confirmed")
        private int confirmed;
        @SerializedName("deaths")
        private int deaths;
        @SerializedName("recovered")
        private int recovered;

        public int getConfirmed() {
            return confirmed;
        }

        public int getDeaths() {
            return deaths;
        }

        public int getRecovered() {
            return recovered;
        }
    }

    @Override
    public String toString() {
        return country;

    }
}
