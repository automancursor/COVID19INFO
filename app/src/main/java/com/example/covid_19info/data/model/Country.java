package com.example.covid_19info.data.model;

public class Country {
    private String country;
    private String iso3;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    @Override
    public String toString() {
        return country + " - " + '(' + iso3 + ')';
    }
}
