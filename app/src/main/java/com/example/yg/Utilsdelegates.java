package com.example.yg;

import java.util.ArrayList;

public class Utilsdelegates {
    static ArrayList<sitizen> sitizens = new ArrayList<>();
    static ArrayList<String> categories = new ArrayList<>();

    public static void FillCategoryData() {
        categories.add("الجغل المجغلل");
        categories.add("مسعد سعيد");
        categories.add("حسام اليافعي");
        categories.add("احمد سعيد");
        categories.add("محمد سعيد");
    }

    public static void FillSitizensData() {
        sitizens.add(new sitizen("احمد احمد", "777888999", "1", "44", categories.get(0)));
        sitizens.add(new sitizen("احمد احمد", "777888999", "1", "44", categories.get(0)));
        sitizens.add(new sitizen("احمد احمد", "777888999", "1", "44", categories.get(0)));
        sitizens.add(new sitizen("احمد احمد", "777888999", "1", "44", categories.get(0)));
        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
        sitizens.add(new sitizen("محمد مرشد ناجي", "777888999", "1", "44", categories.get(1)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(2)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(3)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(3)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(3)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(3)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(3)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(4)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(4)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(4)));
        sitizens.add(new sitizen("احمد", "777888999", "1", "44", categories.get(4)));
    }

    public static void FillData() {
        FillCategoryData();
        FillSitizensData();
    }

    public static ArrayList<sitizen> GetSitizensByCategory(String category) {
        ArrayList<sitizen> p = new ArrayList<>();
        for (int i=0;i<sitizens.size();i++){
            if (category.equals(sitizens.get(i).getCategory())){
                p.add(sitizens.get(i));
            }

        }
        return p;
    }
}
