package com.lch.carparking.sharePreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.lch.carparking.core.model.UsersModel;
import com.lch.carparking.utils.ObjectUtils;

import java.util.LinkedHashMap;

/**
 * Created by james on 13/2/2017.
 */

public class MyPreferences {
    private Context context;
    private String preferencesKey;
    private SharedPreferences sharedPreferences;

    public MyPreferences(Context context, String preferencesKey) {
        this.context = context;
        this.preferencesKey = preferencesKey;
        this.sharedPreferences = this.context.getSharedPreferences(this.preferencesKey , this.context.MODE_PRIVATE);
    }


    public void setPreferences_loginInformation(String username, String password){
        clear();
        sharedPreferences.edit().putString("username" , username).apply();
        sharedPreferences.edit().putString("password" , password).apply();

    }

    public void setPreferences_loginInformation(UsersModel usersModel){
//        clear_loginInformation();
        sharedPreferences.edit().putInt("user_id" , usersModel.getId()).apply();
        sharedPreferences.edit().putString("username" , usersModel.getLicense()).apply();
        sharedPreferences.edit().putString("password" , usersModel.getHkid()).apply();

    }

    public LinkedHashMap<String,String> getPreferences_loginInformation(){
        String username = sharedPreferences.getString("username" , "");
        String password = sharedPreferences.getString("password" , "");
        int user_id  = sharedPreferences.getInt("user_id" , 0);

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        if(ObjectUtils.isNotNullEmpty(username))map.put("username",username);
        if(ObjectUtils.isNotNullEmpty(password))map.put("password",password);
        if(user_id > 0 )map.put("user_id",String.valueOf(user_id));
        return map;
    }



    //Clear
    public void clear(){
        sharedPreferences.edit().clear().commit();
    }

}