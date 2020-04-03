package com.epsi.egostyleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> titi = new ArrayList<>();
    public ArrayList<String> dates = new ArrayList<>();
    String toto = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView2);
        /******************************************************/
       RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.1.96/EgoStyleAPI/android_connect/api_all_coupons.php"; // on doit mettre l'adresse ip privée en dur car localhost ne fonctionne pas

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        JSONObject coupon = null;
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(response);
                            for(int i=0; i<jsonArray.length(); i++){
                                coupon = (JSONObject) jsonArray.get(i);
                                toto = coupon.getString("description");
                                Log.d("flipper", "Value: " + toto);
//                                m_coupons.add(Pair.create(coupon.getString("description"), coupon.getString("date_limite")));
                            }
//                            Log.d("toto", "val:"+m_coupons);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        /******************************************************/

        final RecyclerView rv = findViewById(R.id.ListCoupon);
        rv.setLayoutManager(new LinearLayoutManager(this)); //positionnement des éléments
        rv.setAdapter(new ListeAdapter(titi,dates)); //contenu de la liste

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            //mesbons c'est l'id du bouton pour acceder aux coupons
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mesbons :
                        startActivity(new Intent(getApplicationContext(),
                                CouponActivity.class));
                        finish();
                                overridePendingTransition(0,0);
                                return true;
                    case R.id.scanner :
                        startActivity(new Intent(getApplicationContext(),
                                ScanActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home :
                        return true;
                }
                return false;
            }
        });
    }
}
