package com.lch.carparking.core.presenter;

import android.support.v4.app.Fragment;

import com.lch.carparking.core.model.MainModel;
import com.lch.carparking.core.view.MainView;

public class MainPresenter {
    private MainView mainView;
    private MainModel mainModel;

    public MainPresenter(MainView mainView, MainModel mainModel) {
        this.mainView = mainView;
        this.mainModel = mainModel;
    }

    public void onCreate(){
        mainView.setContentView();
        mainView.setSupportActionBar();
        mainView.setNav();
    }

    public void onClickNav(String msg){
        mainView.showToast(msg);
    }
    public boolean checkPermission(){
        return mainView.setPermission();
    }


    public void showFagment(Fragment fragment){
        mainView.showFagment(fragment);
    }

    public void setBtnHeader(){
        mainView.setBtnHeader();
    }
}