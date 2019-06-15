package com.lch.carparking.core.presenter;

import com.lch.carparking.core.model.CarParkingModel;
import com.lch.carparking.core.view.CarParkView;

import java.util.List;

public class CarParkPresenter {
    private CarParkView carParkView;

    public CarParkPresenter(CarParkView carParkView) {
        this.carParkView = carParkView;
    }

    public void updateGridView(List<CarParkingModel> a){
        this.carParkView.updateGridView( a);
    }
}
