package buspubsub;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class Value {

    private Bus bus;
    private double longitude;
    private double latitude;

    public Value(Bus bus, double longitude, double latitude) {
        this.bus = bus;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Value{" + "bus=" + bus + ", longitude=" + longitude + ", latitude=" + latitude + '}';
    }

}
