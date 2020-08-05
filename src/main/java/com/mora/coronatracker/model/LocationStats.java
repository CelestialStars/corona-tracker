package com.mora.coronatracker.model;

public class LocationStats {
    private String state;
    private String country;
    private int latestTotalConfirmedCases;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalConfirmedCases() {
        return latestTotalConfirmedCases;
    }

    public void setLatestTotalConfirmedCases(int latestTotalConfirmedCases) {
        this.latestTotalConfirmedCases = latestTotalConfirmedCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "country='" + country + '\'' +
                ", latestTotalConfirmedCases=" + latestTotalConfirmedCases +
                '}';
    }
}
