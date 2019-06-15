package com.lch.carparking.core.view;

import android.support.v4.app.Fragment;

public interface MainView {
    boolean setPermission();
    void setContentView();
    void setNav();
    void setSupportActionBar();
    void showToast(String msg);
    void showFagment(Fragment fragment);
    void setBtnHeader();
}