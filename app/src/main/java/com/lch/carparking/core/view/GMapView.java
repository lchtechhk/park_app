package com.lch.carparking.core.view;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public interface GMapView {
    boolean getCurrentLocation();
    void placeMarkerOnMap(LatLng location , String name);
    List<LatLng> setDirection(String d1 , String d2);
    void printDirection();
}
