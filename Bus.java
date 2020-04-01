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
public class Bus {

    private String routeCode;
    private String lineNumber;
    private String vehicleId;
    private String lineName;
    private String busLineId;
    private String info;

    public Bus(String routeCode, String lineNumber, String vehicleId, String lineName, String busLineId, String info) {
        this.routeCode = routeCode;
        this.lineNumber = lineNumber;
        this.vehicleId = vehicleId;
        this.lineName = lineName;
        this.busLineId = busLineId;
        this.info = info;
    }

    Bus() {
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getBusLineId() {
        return busLineId;
    }

    public void setBusLineId(String busLineId) {
        this.busLineId = busLineId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Bus{" + "routeCode=" + routeCode + ", lineNumber=" + lineNumber + ", vehicleId=" + vehicleId + ", lineName=" + lineName + ", busLineId=" + busLineId + ", info=" + info + '}';
    }

}
