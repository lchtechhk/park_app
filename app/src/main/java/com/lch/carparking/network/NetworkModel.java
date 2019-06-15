package com.lch.carparking.network;

import android.content.Context;

/**
 * Created by Jamie on 15/7/2017.
 */

public class NetworkModel {

    private Context appCompatActivity;

    public NetworkModel() {
    }

    public NetworkModel(Context appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public Context getAppCompatActivity() {
        return appCompatActivity;
    }

    public void setAppCompatActivity(Context appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }
}
