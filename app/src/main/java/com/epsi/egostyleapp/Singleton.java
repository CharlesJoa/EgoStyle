package com.epsi.egostyleapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.core.util.Pair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Singleton {

    private final static Singleton instance = new Singleton();

    public ArrayList<Pair<String, String>> pairs_bons = new ArrayList<>();

    public String description_bon;
    public String date_limite_bon;
    public String codepromotion_bon;


    public final static Singleton getInstance() {
        return instance;
    }

    //retourne la liste
    public ArrayList<Pair<String, String>> getPairList(){
        return pairs_bons;
    }

    //ajoute un bon à la liste
    public void setPairList(Pair<String, String> pair){
        this.pairs_bons.add(pair);
    }

}
