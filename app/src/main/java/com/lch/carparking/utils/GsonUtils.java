package com.lch.carparking.utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class GsonUtils {
    public static Gson toJson(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        return gson;
    }

    // No Chage anythings (Date Format is Basic)
    public static Gson toBaseJson(){
        Gson gson = new Gson();
        return gson;
    }
    public static Gson fromJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            //            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            @Override
            public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {
                try {
//                    Log.d("asd","json.getAsLong() : " +json.getAsLong());
                    Date newDate = new Date(json.getAsLong());
//                    Log.d("asd","newDate : " +newDate);
                    return newDate;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        Gson gson = gsonBuilder.create();
        return gson;
    }

    public static Gson fromStringJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            @Override
            public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {
                try {
                    String Date_str = json.getAsString();
//                    Log.v("asd","json.getAsLong() : " +json.getAsString());
                    Date newDate = df.parse(Date_str);
//                    Log.v("asd","newDate : " +newDate);
                    return newDate;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        Gson gson = gsonBuilder.create();
        return gson;
    }

    public static String modelToJson(Object model){
        String result = null;
        try{
            Gson gson = GsonUtils.toJson();
            result = gson.toJson(model);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
