package com.lch.carparking.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ObjectUtils {
    public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat sdf_onlyDate = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean isNotNullEmpty(String string) {
        return string != null && !string.equals("") && !string.equals("null") && !string.isEmpty() && !string.trim().isEmpty();
    }

    public static boolean isCorrectResponse(String result){
        if(ObjectUtils.isNullEmpty(result)){
            return false;
        }
        if(result.equals(AsyncMessage.error_network)){
            return false;
        }
        return true;
    }

    public static  boolean isNullEmpty(String data){
        boolean result = false;

        if(data == null || "".equals(data) || data.isEmpty()){
            result = true;
        }

        return result;
    }

    public static  String longToString(Long data){
        String result = "";
        if(data != null){
            result = data.toString();
        }
        return result;
    }
    public static boolean isLongValue(String data){
        boolean result = true;
        if(!data.matches("\\d+(?:\\.\\d+)?")) {
            result = false;
        }
        return result;
    }

    public static String bigDecimalToString (BigDecimal bigDecimal){
        String value= "";
        if (bigDecimal != null){
            value = bigDecimal.toString();
        }
        return value;
    }

    public static Integer stringToInteger(String string){
        Integer integer = 0;
        if(isNotNullEmpty(string)){
            integer = Integer.parseInt(string);
            return integer;
        }
        return integer;
    }

    public static String intToString(Integer integer){
        String string = "";
        if(integer != null){
            string = integer.toString();
        }
        return  string;
    }
    public static BigDecimal stringToBigDecimal(String string){
        BigDecimal bigDecimal = new BigDecimal(0);
        if(isNotNullEmpty(string)){
            bigDecimal = new BigDecimal(string);
            return bigDecimal;
        }
        return  bigDecimal;
    }
    public static Date stringToDate(String string){
        Date date = null;
        if(isNotNullEmpty(string)){
            try {
                date = sdf.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
    public static Date stringToDate_onlyDate(String string){
        Date date = null;
        if(isNotNullEmpty(string)){
            try {
                date = sdf_onlyDate.parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
    public static String dateToString(Date date){
        String string = "";
        if(date != null){
            string = sdf.format(date);
        }
        return string;
    }

    public static String dateToString_OnlyDate(Date date){
        String string = "";
        if(date != null){
            string = sdf_onlyDate.format(date);
        }
        return string;
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

}