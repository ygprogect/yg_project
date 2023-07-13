package com.example.yg.Server;

public class URLs {
//    192.168.43.128:8080\api\citizen\login
    public static final String URL = "http://192.168.43.128:8080/";
//    public static final String URL = "http://192.168.9.128::8080/";
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
    public static final String GetOrders = HOME+"/delegate/get_orders";
    public static final String GetAqelOrders = HOME+"/aqel/get_orders";
    public static final String GetOrderCitizens = HOME+"/aqel/get_order_citizens";
    public static final String GetDelegateOrderCitizens = HOME+"/delegate/get_order_citizens";
    public static final String UpdateDetails = HOME+"/delegate/update_details";
    public static final String GetDetails = HOME+"/delegate/get_details";

}
