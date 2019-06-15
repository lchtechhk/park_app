package com.lch.carparking.dao;

import android.content.Context;
import android.util.Log;

import com.lch.carparking.core.model.UsersModel;
import com.lch.carparking.network.HttpPostAsync;
import com.lch.carparking.network.NetworkModel;
import com.lch.carparking.network.ResponseModel;
import com.lch.carparking.network.ServeProfile;
import com.lch.carparking.utils.GsonUtils;
import com.lch.carparking.utils.ObjectUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

public class UsersDaoImpl extends NetworkModel implements UsersDao<UsersModel>{

    public UsersDaoImpl(Context appCompatActivity) {
        super(appCompatActivity);
    }
    public UsersDaoImpl() {
    }
    @Override
    public ResponseModel save(String json) {
        String result = "";
        try {
            String link = ServeProfile.serve+ServeProfile.register;
            result = new HttpPostAsync(this).execute(link,json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ResponseModel]-save-[Response(String)] : "+result);
        if(!ObjectUtils.isCorrectResponse(result)){
            return null;
        }
        //
        ResponseModel responseModel = null;
        try{
            Gson gson = GsonUtils.fromJson();
            Type listType = new TypeToken<ResponseModel>() {}.getType();
            responseModel = gson.fromJson(result,listType);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(responseModel == null){
            Log.d("asd","[ResponseModel]-save(Response) [Error] : Server have not response anything");
            return null;
        }
        Log.d("asd","[ResponseModel]-save(Response)  :" + responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel login(String json) {
        String result = "";
        try {
            String link = ServeProfile.serve+ServeProfile.login;
            result = new HttpPostAsync(this).execute(link,json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ResponseModel]-login-[Response(String)] : "+result);
        if(!ObjectUtils.isCorrectResponse(result)){
            return null;
        }
        //
        ResponseModel responseModel = null;
        try{
            Gson gson = GsonUtils.fromJson();
            Type listType = new TypeToken<ResponseModel>() {}.getType();
            responseModel = gson.fromJson(result,listType);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(responseModel == null){
            Log.d("asd","[ResponseModel]-save(Response) [Error] : Server have not response anything");
            return null;
        }
        Log.d("asd","[ResponseModel]-save(Response)  :" + responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel register_login(String json) {
        String result = "";
        try {
            String link = ServeProfile.serve + ServeProfile.register_login;
            result = new HttpPostAsync(this).execute(link, json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:", "[ResponseModel]-register_login-[Response(String)] : " + result);
        if (!ObjectUtils.isCorrectResponse(result)) {
            return null;
        }
        //
        ResponseModel responseModel = null;
        try {
            Gson gson = GsonUtils.fromJson();
            Type listType = new TypeToken<ResponseModel>() {
            }.getType();
            responseModel = gson.fromJson(result, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (responseModel == null) {
            Log.d("asd", "[ResponseModel]-register_login(Response) [Error] : Server have not response anything");
            return null;
        }
        Log.d("asd", "[ResponseModel]-register_login(Response)  :" + responseModel.toString());
        return responseModel;
    }
}
