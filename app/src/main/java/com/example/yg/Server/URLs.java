package com.example.yg.Server;

public class URLs {
//    192.168.43.128:8080\api\citizen\login
    public static final String URL = "http://192.168.43.128:8080/";
    public static final String HOME = URL+"api";
    public static final String CITIZEN_LOGIN = HOME+"/citizen/login";
    public static final String AQEL_LOGIN = HOME+"/aqel/login";
    public static final String DELIVERY_LOGIN = HOME+"/delivery/login";
    public static final String DELEGATE_LOGIN = HOME+"/delegate/login";
    public static final String CITIZEN_LOGOUT = HOME+"/citizen/logout";
    public static final String AQEL_LOGOUT = HOME+"/aqel/logout";
    public static final String DELIVERY_LOGOUT = HOME+"/delivery/logout";
    public static final String DELEGATE_LOGOUT = HOME+"/delegate/logout";
    public static final String GetCitizens = HOME+"/delegate/get_citizens";
}