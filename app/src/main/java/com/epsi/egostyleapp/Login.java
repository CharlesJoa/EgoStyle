package com.epsi.egostyleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.btn = (Button) findViewById(R.id.buttonlogin);
        this.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Login.this, MainActivity.class);
                //Intent loginIntent = new Intent(SplashScreen.this, Main2Activity.class);

                Login.this.startActivity(loginIntent);
                Login.this.finish();
            }
        });
    }
}