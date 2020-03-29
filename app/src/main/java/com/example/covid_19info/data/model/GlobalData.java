package com.example.covid_19info.data.model;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(value = Parcel.Serialization.BEAN)
public class GlobalData {

    @SerializedName("country")
    private String country;
    @SerializedName("cases")
    private int cases;
    @SerializedName("todayCases")
    private int todayCases;
    @SerializedName("deaths")
    private int deaths;
    @SerializedName("todayDeaths")
    private int todayDeaths;
    @SerializedName("recovered")
    private int recovered;
    @SerializedName("active")
    private int active;
    @SerializedName("critical")
    private int critical;
    @SerializedName("casesPerOneMillion")
    private int casesPerOneMillion;
    @SerializedName("deathsPerOneMillion")
    private int deathsPerOneMillion;
    @SerializedName("countryInfo")
    private String countryInfo;
    
    public String getCountry() {
        return country;
    }

    public int getCases() {
        return cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getActive() {
        return active;
    }

    public int getCritical() {
        return critical;
    }

    public int getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public int getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public String getCountryInfo() {
        return countryInfo;
    }

    private static class CountryInfo {

        @SerializedName("_id")
        private int confirmed;
        @SerializedName("country")
        private String country;
        @SerializedName("iso2")
        private String iso2;
        @SerializedName("iso3")
        private String iso3;
        @SerializedName("flag")
        private String flag;
        @SerializedName("lat")
        private long lat;
        @SerializedName("long")
        private long lng;

        public int getConfirmed() {
            return confirmed;
        }

        public String getCountry() {
            return country;
        }

        public String getIso2() {
            return iso2;
        }

        public String getIso3() {
            return iso3;
        }

        public String getFlag() {
            return flag;
        }

        public long getLat() {
            return lat;
        }

        public long getLng() {
            return lng;
        }
    }
}
