package com.example.carsapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    public static Context Context;

    public static void StoreData(List<Car> cars)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Context);
        SharedPreferences.Editor editor = sp.edit();
        String json = new Gson().toJson(cars );
        editor.putString("cars", json);
        editor.commit();
    }

    public static List<Car> LoadData()
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Context);
        String json = sp.getString("cars",null);
        if (json != null)
        {
            Type type = new TypeToken<List<Car>>(){}.getType();
            return new Gson().fromJson(json,type);
        }
        else
        {
            List<Car> cars = new ArrayList<Car>();
            cars.add(new Car(R.drawable.car1,"Toyata","i30","2017","80,000"));
            cars.add(new Car(R.drawable.car2,"Honda","g45","2019","50,000"));
            cars.add(new Car(R.drawable.car3,"Hunday","v78","2020","90,000"));
            cars.add(new Car(R.drawable.car4,"Bmw","n44","2021","120,000"));
            cars.add(new Car(R.drawable.car5,"Mercedez","nj8","2021","120,000"));
            cars.add(new Car(R.drawable.car6,"Tesla","mm9","2020","200,000"));
            cars.add(new Car(R.drawable.car7,"Purche","cv59","2021","200,000"));
            cars.add(new Car(R.drawable.car8,"Mazeratti","ssa38","2018","180,000"));
            return cars;
        }
    }
}
