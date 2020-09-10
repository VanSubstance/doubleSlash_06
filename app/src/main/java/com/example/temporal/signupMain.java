package com.example.temporal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signupMain extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);

        Button buttonConfirm = findViewById(R.id.buttonConfirm);
        Button buttonCancel = findViewById(R.id.buttonCancel);

        buttonConfirm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editEmail = findViewById(R.id.inputEmail);
                String inputEmail = editEmail.getText().toString();
                EditText editPw = findViewById(R.id.inputPw);
                String inputPw = editPw.getText().toString();
                // 서버 연결
                // 1. 이메일 중복 여부
                finish();
            }
        });

        buttonCancel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}