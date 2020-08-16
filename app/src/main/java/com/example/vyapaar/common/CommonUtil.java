package com.example.vyapaar.common;

import android.app.Application;
import android.content.Context;

import com.example.vyapaar.common.model.State;
import com.example.vyapaar.common.model.StateList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CommonUtil {
    private static StateList states;

    public static StateList getStateList(Context application){
        Gson gson = new Gson();
        states = gson.fromJson(loadJSONFromAsset(application), StateList.class);
        return states;
    }


    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("statelist.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
