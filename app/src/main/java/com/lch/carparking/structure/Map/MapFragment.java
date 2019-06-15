package com.lch.carparking.structure.Map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lch.carparking.R;
import com.lch.carparking.core.model.BottomDialogModel;
import com.lch.carparking.core.model.CarParkingModel;
import com.lch.carparking.core.presenter.GMapPresenter;
import com.lch.carparking.core.view.GMapView;
import com.lch.carparking.element.BottomDialogFragment;
import com.lch.carparking.sharePreferences.MyPreferences;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapFragment extends Fragment implements OnMapReadyCallback, GMapView, View.OnClickListener {

    private GoogleMap mMap;

    //Register
    GMapPresenter gMapPresenter;
    //Permission
    private static final int INITIAL_REQUEST = 1337;
    private static final int LOCATION_REQUEST = INITIAL_REQUEST + 3;

    //Location
    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;
    private Location mLastLocation;
    private LocationManager lm;
    private double longitude, latitude = 0.00;

    GeoApiContext context;

    ArrayList<HashMap<String, Object>> location_map;
    double park1_lat = 22.3123397;
    double park1_lng = 114.2238104;
    double park2_lat = 22.3254694;
    double park2_lng = 114.2046898;
    double park3_lat = 22.3234889;
    double park3_lng = 114.2087693;
    LatLng park1 = new LatLng(park1_lat, park1_lng);
    LatLng park2 = new LatLng(park2_lat, park2_lng);
    LatLng park3 = new LatLng(park3_lat, park3_lng);

    @BindView(R.id.location_search)
    Button location_search;
    @BindView(R.id.mapView)
    MapView mMapView;
    @BindView(R.id.location_name)
    EditText location_name;

    MyPreferences myPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_map, container, false);

        ButterKnife.bind(this, rootView);
//        pager.setPagingEnabled(false);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAvNuWpH2YP2wTkAOjYFaX9e3OKoZdpogI")
                .build();


        gMapPresenter = new GMapPresenter(this);
        mMapView.getMapAsync(this);
        //Define list to get all latlng for the route
        location_search.setOnClickListener(this);
        myPreferences = new MyPreferences(getActivity(),"login_information");

        return rootView;
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.w("asd","onLocationChanged");

            latitude = location.getLatitude();
            longitude = location.getLongitude();

            LatLng sydney = new LatLng(latitude, longitude);
//        mMap.addMarker(new MarkerOptions().position(sydney).title(""));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.w("asd","onStatusChanged");

        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.w("asd","onProviderEnabled");

        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.w("asd","onProviderDisabled");

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.location_search:
                String location = location_name.getText().toString();
                if(TextUtils.isEmpty(location)){
                    location_name.setError("必需輸入");
                    location_name.requestFocus();
                    return;
                }

                gMapPresenter.placeMarkerOnMap(park1 , "Park1");
                gMapPresenter.placeMarkerOnMap(park2 , "Park2");
                gMapPresenter.placeMarkerOnMap(park3 , "Park3");

                location_map = new ArrayList<>();
                HashMap<String,Object> hp = new HashMap<>();
                CarParkingModel parkingModel = new CarParkingModel();
                parkingModel.setId(1);
                parkingModel.setCode("a1");
                parkingModel.setLat(park1_lat);
                parkingModel.setLng(park1_lng);
                LatLng sydney1 = new LatLng(latitude, longitude);
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(park1));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(park1, 13));
                hp.put("parkId",1);
                hp.put("parkingModel",parkingModel);
                location_map.add(hp);

                hp = new HashMap<>();
                parkingModel = new CarParkingModel();
                parkingModel.setId(2);
                parkingModel.setCode("A2");
                parkingModel.setLat(park2_lat);
                parkingModel.setLng(park2_lng);
                hp.put("parkId",2);
                hp.put("parkingModel",parkingModel);
                location_map.add(hp);

                hp = new HashMap<>();
                parkingModel = new CarParkingModel();
                parkingModel.setId(3);
                parkingModel.setCode("A3");
                parkingModel.setLat(park3_lat);
                parkingModel.setLng(park3_lng);
                hp.put("parkId",3);
                hp.put("parkingModel",parkingModel);
                location_map.add(hp);

                List color_list = new ArrayList();
                color_list.add(Color.parseColor("#4db8ff"));
                color_list.add(Color.parseColor("#b3b3ff"));
                color_list.add(Color.parseColor("#FF3333"));

                for(int i=0; i<location_map.size();i++){
                    CarParkingModel p = (CarParkingModel)location_map.get(i).get("parkingModel");
                    List<LatLng> path = gMapPresenter.setDirection(Double.toString(p.getLat()),Double.toString(p.getLng()));
//                    if(path != null ){
                        location_map.get(i).put("path",path);
                        location_map.get(i).put("color",color_list.get(i));
//                    }
                }
                gMapPresenter.printDirection();
                BottomDialogModel bottomDialogModel = new BottomDialogModel();
                bottomDialogModel.setLeft_time("20分鐘");
                bottomDialogModel.setLeft_distant("100米");
                bottomDialogModel.setLeft_charge("$30/1H");

                bottomDialogModel.setMiddle_time("30分鐘");
                bottomDialogModel.setMiddle_distant("800米");
                bottomDialogModel.setMiddle_charge("$20/1H");

                bottomDialogModel.setRight_time("60分鐘");
                bottomDialogModel.setRight_distant("2公里");
                bottomDialogModel.setRight_charge("$20/1H");


                BottomDialogFragment bottomSheetDialog = new BottomDialogFragment(bottomDialogModel);
                bottomSheetDialog.setCancelable(true);
                bottomSheetDialog.show(getActivity().getSupportFragmentManager(), "Custom Bottom Sheet");

            break;
        }
    }

    @Override
    public void printDirection() {
        for (int i=0; i<location_map.size(); i++){
            List<LatLng> path = (List<LatLng>)location_map.get(i).get("path");
            int color = (int) location_map.get(i).get("color");
            PolylineOptions opts = new PolylineOptions().addAll(path).color(color).width(12);
            mMap.addPolyline(opts);
        }
    }

    @SuppressWarnings({"MissingPermission"})
    @Override
    public void onMapReady(GoogleMap googleMap) {
        lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1f, locationListener);

        gMapPresenter.getCurrentLocation();
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);  // 右下角的放大縮小功能
        LatLng sydney = new LatLng(latitude, longitude);
//        mMap.addMarker(new MarkerOptions().position(sydney).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
    }

    @Override
    public List<LatLng> setDirection(String destination_lat, String destination_lng) {
        List<LatLng> path = new ArrayList();
        DirectionsApiRequest req = DirectionsApi.getDirections(context, latitude+","+longitude, destination_lat+","+destination_lng);
        try {
            DirectionsResult res = req.await();
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];
                if (route.legs !=null) {
                    for(int i=0; i<route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j=0; j<leg.steps.length;j++){
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length >0) {
                                    for (int k=0; k<step.steps.length;k++){
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                }else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch(Exception ex) {
        }
        //Draw the polyline
        if (path.size() > 0) {
            return path;
//            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
//            mMap.addPolyline(opts);
        }
        return null;
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public boolean getCurrentLocation() {
        String provider = lm.getBestProvider(new Criteria(), true);        Location isGPSEnabled  = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location isNetworkEnabled   = lm.getLastKnownLocation(provider);
        if(isNetworkEnabled != null ){
            longitude = isNetworkEnabled.getLongitude();
            latitude = isNetworkEnabled.getLatitude();
        }else {
            Toast.makeText(getActivity(),"沒有 location provider 可以使用",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
//        if(isGPSEnabled != null ){
//            longitude = isGPSEnabled.getLongitude();
//            latitude = isGPSEnabled.getLatitude();
//        }
    }

    @Override
    public void placeMarkerOnMap(LatLng location , String name) {
        mMap.addMarker(new MarkerOptions().position(location).title(name));
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}