package com.lch.carparking.network;

public class ServeProfile {
        //    public static String serve = "http://123.203.119.83:1123/";    // HOME
        //    var serve = "http://192.168.0.3:1123/" //Mobile
//        public static String serve = "http://192.168.0.4/report_parking/";
        public static String serve = "http://13.229.0.90/park/";

        public static String findAll = "parking_controller.php?operation=findAll";
        public static String findByUserId = "parking_controller.php?operation=findByUserId";
        public static String holding = "parking_controller.php?operation=holding";
        public static String p_a = "parking_controller.php?operation=p_a";
        public static String payment = "parking_controller.php?operation=payment";
        public static String arrival = "parking_controller.php?operation=arrival";
        public static String leave = "parking_controller.php?operation=leave";

        public static String register = "user_controller.php?operation=save";
        public static String login = "user_controller.php?operation=login";
        public static String register_login = "user_controller.php?operation=register_login";


}
