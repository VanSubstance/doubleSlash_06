package com.example.temporal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                EditText editEmail = findViewById(R.id.inputEmail);
                String inputEmail = editEmail.getText().toString();
                EditText editPw = findViewById(R.id.inputPw);
                String inputPw = editPw.getText().toString();
                // 서버 연결
                // 1. 이메일 / 패스워드 확인
                if (inputEmail.equals("asd") && inputPw.equals("asd")) {
                    startActivity(intentSignin);
                }
                else {
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }
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