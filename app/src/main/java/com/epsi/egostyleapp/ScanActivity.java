package com.epsi.egostyleapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ScanActivity extends AppCompatActivity {
    public String description_bon;
    public String date_limite_bon;
    public String codepromotion_bon;
    public ArrayList<Pair<String, String>> pairs = new ArrayList<>();
    public ArrayList<String> list_already_scan;
    private Button btn_scan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**********Base***********/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);


        /**********Navigation************/
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.scanner);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            //mesbons c'est l'id du bouton pour acceder aux coupons
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mesbons:
                        startActivity(new Intent(getApplicationContext(),
                                BonActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.scanner:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


        /**********Scanner***********/
        btn_scan = (Button) findViewById(R.id.btn_scan);
        final Activity activity = this;
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Impossible de scanner", Toast.LENGTH_LONG).show();
            } else {
                /** 1 - Récupère le phrase code **/
                String phrase_code = result.getContents();
                //String phrase_code = "48957E04E8DF451A8844D4BD94AF705F"; // code en dur pour test

                /** 2 - Test si déjà scanner **/
                for (int i = 0; i < list_already_scan.size(); i++) {
                    String phraseEssai = list_already_scan.get(i);
                    if (phraseEssai.equals(phrase_code)) {
                        Toast.makeText(this, "Code déjà scanner", Toast.LENGTH_LONG).show();
                    } else {
                        list_already_scan.add(phrase_code);

                        /** 3 -  partie requete **/
                        RequestQueue queue = Volley.newRequestQueue(this);
                        String url_bon ="http://192.168.56.1/android_connect/api_all_coupons.php?phrase=";          // on doit mettre l'adresse ip privée en dur car localhost ne fonctionne pas
                        String url_api = url_bon+phrase_code;

                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        JSONObject jsonbon = null;
                                        Singleton singleton = Singleton.getInstance();
                                        try {
                                            jsonbon = new JSONObject(response);
                                            description_bon = jsonbon.getString("description");
                                            date_limite_bon = jsonbon.getString("date_limite");
                                            codepromotion_bon = jsonbon.getString("codepromotion");
                                            String code = description_bon + " - " + codepromotion_bon;

                                            Pair<String, String> pair = Pair.create(code, date_limite_bon);
                                            pairs.add(pair);

                                            singleton.setPairList(pair);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },

                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //textView.setText("That didn't work!");
                                        //affichage reponse pb ?
                                    }
                                });
                        queue.add(stringRequest);
                    }
                    Toast.makeText(this,"Code scanné", Toast.LENGTH_LONG).show();
                    }
            }
        }
        else{
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
