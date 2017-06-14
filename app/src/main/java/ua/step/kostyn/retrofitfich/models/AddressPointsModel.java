package ua.step.kostyn.retrofitfich.models;

/**
 * Created by konstantin on 01.06.17.
 */

public class AddressPointsModel {
    private double latitude;
    private double longitude;

    public AddressPointsModel(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
