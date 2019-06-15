package com.lch.carparking.network;

import com.lch.carparking.core.model.CarParkingModel;
import com.lch.carparking.core.model.UsersModel;

import java.util.List;

public class ResponseModel {
    private boolean message_status;
    private String message;
    List<CarParkingModel> park_data;
    UsersModel user_data;

    public UsersModel getUser_data() {
        return user_data;
    }

    public void setUser_data(UsersModel user_data) {
        this.user_data = user_data;
    }

    public boolean isMessage_status() {
        return message_status;
    }

    public void setMessage_status(boolean message_status) {
        this.message_status = message_status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CarParkingModel> getPark_data() {
        return park_data;
    }

    public void setPark_data(List<CarParkingModel> park_data) {
        this.park_data = park_data;
    }
}
