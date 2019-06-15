package com.lch.carparking.core.presenter;

import com.lch.carparking.core.view.GMapView;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class GMapPresenter {
    private GMapView gMapView;

    public GMapPresenter(GMapView gMapView) {
        this.gMapView = gMapView;
    }

    public void getCurrentLocation(){
        this.gMapView.getCurrentLocation();
    }

    public void placeMarkerOnMap(LatLng location , String name){
        this.gMapView.placeMarkerOnMap(location,name);
    }
    public List<LatLng> setDirection(String a, String b){
        return this.gMapView.setDirection(a,b);
    }

    public void printDirection(){
        this.gMapView.printDirection();
    }
}
