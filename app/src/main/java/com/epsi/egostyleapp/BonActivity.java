package com.epsi.egostyleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BonActivity extends AppCompatActivity {

    public String description_bon;
    public String date_limite_bon;
    public String codepromotion_bon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**********Base***********/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bon);

        final TextView textView = (TextView) findViewById(R.id.textView2);
        final RecyclerView rv = findViewById(R.id.ListBonValide);

        Singleton singleton = Singleton.getInstance();
        ArrayList<Pair<String, String>> pairs = singleton.getPairList();
        rv.setAdapter(new ListeAdapter(pairs)); //contenu de la liste
        rv.setLayoutManager(new LinearLayoutManager(this)); //positionnement des éléments*/

        /**
        //************Affichage d'un bon*********/
        //Appel de l'API/
        /*
        RequestQueue queue = Volley.newRequestQueue(this);
        String url_bon ="http://192.168.56.1/android_connect/api_all_coupons.php?phrase="; // on doit mettre l'adresse ip privée en dur car localhost ne fonctionne pas
        String phrase_bon = "48957E04E8DF451A8844D4BD94AF705F";
        String url_api = url_bon+phrase_bon;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonbon = null;
                        try {
                            jsonbon = new JSONObject(response);
                            description_bon = jsonbon.getString("description");
                            date_limite_bon = jsonbon.getString("date_limite");
                            codepromotion_bon = jsonbon.getString("codepromotion");
                            String code = description_bon+" - "+codepromotion_bon;
                            //textView.setText(code);
                            Pair<String,String> pair = Pair.create(code, date_limite_bon);
                            pairs.add(pair);

                            rv.setAdapter(new ListeAdapter(pairs)); //contenu de la liste
                        }

                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }

        });
        queue.add(stringRequest);

        rv.setLayoutManager(new LinearLayoutManager(this)); //positionnement des éléments*/

        /**********Navigation************/
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.mesbons);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            //mesbons c'est l'id du bouton pour acceder aux coupons
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mesbons :
                        return true;
                    case R.id.scanner :
                        startActivity(new Intent(getApplicationContext(),
                                ScanActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home :
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
