package com.lch.carparking.dao;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lch.carparking.network.HttpPostAsync;
import com.lch.carparking.network.NetworkModel;
import com.lch.carparking.network.ResponseModel;
import com.lch.carparking.network.ServeProfile;
import com.lch.carparking.core.model.CarParkingModel;
import com.lch.carparking.utils.GsonUtils;
import com.lch.carparking.utils.ObjectUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CarparkDaoImpl extends NetworkModel implements CarparkDao<CarParkingModel>{

    public CarparkDaoImpl(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    @Override
    public List<CarParkingModel> findAll() {
        String result = "";
        try {
            String link = ServeProfile.serve+ServeProfile.findAll;
            result = new HttpPostAsync(this).execute(link,"").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[CarParkingModel]-findAll-[Response(String)] : "+result);
        if(!ObjectUtils.isCorrectResponse(result)){
            return null;
        }
        //
        List<CarParkingModel> carParkingModels = new ArrayList<>();
        try{
            Gson gson = GsonUtils.fromJson();
            Type listType = new TypeToken<List<CarParkingModel>>() {}.getType();
            carParkingModels = gson.fromJson(result,listType);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(carParkingModels == null){
            Log.d("asd","[CarParkingModel]-findAll(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[CarParkingModel]-findAll(Response)  :" + carParkingModels.toString());
        return carParkingModels;
    }

    @Override
    public List<CarParkingModel> findByUserId(String json) {
        String result = "";
        try {
            String link = ServeProfile.serve+ServeProfile.findByUserId;
            result = new HttpPostAsync(this).execute(link,json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[CarParkingModel]-findByUserId-[Response(String)] : "+result);
        if(!ObjectUtils.isCorrectResponse(result)){
            return null;
        }
        //
        List<CarParkingModel> carParkingModels = new ArrayList<>();
        try{
            Gson gson = GsonUtils.fromJson();
            Type listType = new TypeToken<List<CarParkingModel>>() {}.getType();
            carParkingModels = gson.fromJson(result,listType);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(carParkingModels == null){
            Log.d("asd","[CarParkingModel]-findByUserId(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[CarParkingModel]-findByUserId(Response)  :" + carParkingModels.toString());
        return carParkingModels;
    }

    @Override
    public ResponseModel request_holding(String json) {
        String result = "";
        try {
            String link = ServeProfile.serve+ServeProfile.holding;
            result = new HttpPostAsync(this).execute(link,json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ResponseModel]-request_holding-[Response(String)] : "+result);
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
            Log.d("asd","[ResponseModel]-request_holding(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ResponseModel]-request_holding(Response)  :" + responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel p_a(String json) {
        String result = "";
        try {
            String link = ServeProfile.serve+ServeProfile.p_a;
            result = new HttpPostAsync(this).execute(link,json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ResponseModel]-p_a-[Response(String)] : "+result);
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
            Log.d("asd","[ResponseModel]-p_a(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ResponseModel]-p_a(Response)  :" + responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel request_payment(String json) {
        String result = "";
        try {
            String link = ServeProfile.serve+ServeProfile.payment;
            result = new HttpPostAsync(this).execute(link,json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ResponseModel]-request_payment-[Response(String)] : "+result);
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
            Log.d("asd","[ResponseModel]-request_payment(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ResponseModel]-request_payment(Response)  :" + responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel request_arrival(String json) {
        String result = "";
        try {
            String link = ServeProfile.serve+ServeProfile.arrival;
            result = new HttpPostAsync(this).execute(link,json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ResponseModel]-request_arrival-[Response(String)] : "+result);
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
            Log.d("asd","[ResponseModel]-request_arrival(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ResponseModel]-request_arrival(Response)  :" + responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel request_leave(String json) {
        String result = "";
        try {
            String link = ServeProfile.serve+ServeProfile.leave;
            result = new HttpPostAsync(this).execute(link,json).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("asd:","[ResponseModel]-request_leave-[Response(String)] : "+result);
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
            Log.d("asd","[ResponseModel]-request_leave(Response) [Error] : Serve have not response anything");
            return null;
        }
        Log.d("asd","[ResponseModel]-request_leave(Response)  :" + responseModel.toString());
        return responseModel;
    }


}
