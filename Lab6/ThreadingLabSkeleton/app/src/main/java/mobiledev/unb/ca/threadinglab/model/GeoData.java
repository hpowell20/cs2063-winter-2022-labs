package mobiledev.unb.ca.threadinglab.model;

public class GeoData {

    // GeoData model attributes
    private String title;
    private String longitude;
    private String latitude;

    // Empty Constructor
    public GeoData() {

    }

    // Setter Methods
    public void setTitle(String title) {
        this.title = title;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    // Getter Methods
    public String getTitle() {
        return title;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
