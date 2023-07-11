package com.example.yg;
//
//import java.util.ArrayList;
//
//public class Utilsdelegates {
//    static ArrayList<sitizen> sitizens = new ArrayList<>();
//    static ArrayList<String> categories = new ArrayList<>();
//
//    public static void FillCategoryData() {
//        categories.add("الجغل المجغلل");
//        categories.add("مسعد سعيد");
//        categories.add("حسام اليافعي");
//        categories.add("احمد سعيد");
//        categories.add("محمد سعيد");
//    }
//
//    public static void FillSitizensData() {
////        sitizens.add(new sitizen("احمد احمد", "777888999", "1", "44", categories.get(0)));
////        sitizens.add(new sitizen("احمد احمد", "777888999", "1", "44", categories.get(0)));
////        sitizens.add(new sitizen("احمد احمد", "777888999", "1", "44", categories.get(0)));
////        sitizens.add(new sitizen("احمد احمد", "777888999", "1", "44", categories.get(0)));
////        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
////        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
////        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
////        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
////        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
////        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(3)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(3)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(3)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(3)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(3)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(4)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(4)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(4)));
////        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(4)));
//    }
//
//    public static void FillData() {
//        FillCategoryData();
//        FillSitizensData();
//    }
//
////    public static ArrayList<sitizen> GetSitizensByCategory(String category) {
////        ArrayList<sitizen> p = new ArrayList<>();
////        for (int i=0;i<sitizens.size();i++){
////            if (category.equals(sitizens.get(i).getCategory())){
////                p.add(sitizens.get(i));
////            }
////
////        }
////        return p;
////    }
//}

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Utilsdelegates {
    private static ArrayList<sitizen> sitizens = new ArrayList<>();
    private static ArrayList<catagory> categories = new ArrayList<>();

    public static ArrayList<sitizen> getSitizens() {
        return sitizens;
    }


    public static ArrayList<catagory> getCategories() {
        return categories;
    }

    public static void FillData(Context context) {
        FetchCategoryData(context);
        FetchSitizensData(context);
    }

    private static void FetchCategoryData(Context context) {
        String url = "YOUR_CATEGORY_API_URL"; // ضع رابط API الخاص بالفئات هنا

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                Object category = response.getJSONObject(i).getJSONObject("category");
//                                categories.add(category);
                            }

                            // هنا يمكنك استخدام الفئات المستردة لربطها مع sitizens

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    private static void FetchSitizensData(Context context) {
        String url = "YOUR_SITIZENS_API_URL"; // ضع رابط API الخاص بالمواطنين هنا

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                String name = response.getJSONObject(i).getString("name");
                                String phone = response.getJSONObject(i).getString("phone");
                                int id = response.getJSONObject(i).getInt("id");
                                int card_no = response.getJSONObject(i).getInt("card_no");
                                String ssn = response.getJSONObject(i).getString("ssn");

                                // استخدم تطابق الفئة لربط الفئة بالكائن sitizen
                                sitizen sitizenObj = new sitizen(name, phone, id,card_no, ssn);
                                sitizens.add(sitizenObj);
                            }

                            // هنا يمكنك استخدام sitizens و categories المستردة معًا

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    // ... أي دوال أخرى تحتاجها لتنظيم وإدارة البيانات

}
