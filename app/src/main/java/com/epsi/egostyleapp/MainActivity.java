package com.epsi.egostyleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView rv = findViewById(R.id.ListCoupon);
        rv.setLayoutManager(new LinearLayoutManager(this)); //positionnement des éléments
        rv.setAdapter(new ListeAdapter()); //contenu de la liste

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


        /****************************************************/

        final TextView textView = (TextView) findViewById(R.id.textView2);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://172.20.10.3/android_connect/"; // on doit mettre l'adresse ip privée en dur car localhost ne fonctionne pas

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: "+ response.substring(0,500));
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

    }
}
