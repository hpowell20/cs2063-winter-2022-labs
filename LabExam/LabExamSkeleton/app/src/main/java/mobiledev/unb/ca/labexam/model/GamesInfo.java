package mobiledev.unb.ca.labexam.model;

import androidx.annotation.NonNull;

public class GamesInfo {

    private final String year;
    private final String number;
    private final String hostCity;
    private final String dates;
    private final String wikipediaLink;

    private GamesInfo(Builder builder) {
        this.year = builder.year;
        this.number = builder.number;
        this.hostCity = builder.hostCity;
        this.dates = builder.dates;
        this.wikipediaLink = builder.wikipediaLink;
    }

    // Only need to include getters
    public String getTitle() {
        return number + " - " + hostCity;
    }

    public String getYear() {
        return year;
    }

    public String getNumber() {
        return number;
    }

    public String getHostCity() {
        return hostCity;
    }

    public String getDates() {
        return dates;
    }

    public String getWikipediaLink() {
        return wikipediaLink;
    }

    public static class Builder {
        private String year;
        private String number;
        private String hostCity;
        private String dates;
        private String wikipediaLink;

        public Builder(@NonNull String year,
                       @NonNull String number,
                       @NonNull String hostCity,
                       @NonNull String dates,
                       @NonNull String wikipediaLink) {
            this.year = year;
            this.number = number;
            this.hostCity = hostCity;
            this.dates = dates;
            this.wikipediaLink = wikipediaLink;
        }

        public GamesInfo build() {
            return new GamesInfo(this);
        }
    }
}
