package com.example.temporal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent splash = new Intent(this, splashMain.class);
        startActivity(splash);

        final Intent intentSignin = new Intent(this, interfaceMain.class);
        final Intent intentSignup = new Intent(this, signupMain.class);
        Button buttonSignin = findViewById(R.id.buttonSignin);
        Button buttonSignup = findViewById(R.id.buttonSignup);

        buttonSignin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentSignin);
            }
        });

        buttonSignup.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentSignup);
            }
        });
    }
}