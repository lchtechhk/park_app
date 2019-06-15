package com.lch.carparking.core.model;

public class CarParkingModel {
    private int id;
    private String code;
    private double amount;
    private int size;
    private double lat;
    private double lng;
    private String operation_status;
    private String status;
    private String booking_status;
    private String booking_status_descr;

    public CarParkingModel() {
    }

    public String getBooking_status_descr() {
        return booking_status_descr;
    }

    public void setBooking_status_descr(String booking_status_descr) {
        this.booking_status_descr = booking_status_descr;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOperation_status() {
        return operation_status;
    }

    public void setOperation_status(String operation_status) {
        this.operation_status = operation_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
