package com.example.temporal;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class signupMain extends Activity {
    EditText editEmail, editPw;
    Button buttonConfirm, buttonCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);

        editEmail = (EditText) findViewById(R.id.inputEmail);
        editPw = (EditText) findViewById(R.id.inputPw);

        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);

        buttonConfirm.setOnClickListener(btnListener);
        buttonCancel.setOnClickListener(btnListener);

    }


    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;

        @Override
        //doinBackground의 매개값은 문자열 배열, 보낼 값이 여러개일 경우를 위해 배열로
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://10.0.2.2:9090/ex/signup.jsp"); // 보낼 jsp 주소를 ""안에 작성
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");  // 데이터를 POST 방식으로 전송
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "email="+strings[0]+"&passward="+strings[1]; // 보낼 정보. GET방식으로 작성. ex)"email=song@n.com&passward=1234";

                osw.write(sendMsg); //OutputStreamWriter에 담아 전송
                osw.flush();

                //jsp와 통신이 정상적으로 되었을 때
                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    // jsp에서 보낸 값
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {
                    // 통신이 실패했을 때 실패한 이유를 알기 위해 로그에 작성
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //jsp로부터 받은 값 리턴
            return receiveMsg;
        }
    }
        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.buttonConfirm:
                        String inputEmail = editEmail.getText().toString();
                        String inputPw = editPw.getText().toString();
                        try {
                            String result  = new CustomTask().execute(inputEmail,inputPw).get();
                            if(result.equals("email")) {
                                Toast.makeText(signupMain.this,"이미 존재하는 이메일입니다.",Toast.LENGTH_SHORT).show();
                                editEmail.setText("");
                                editPw.setText("");
                            } else if(result.equals("ok")) {
                                editEmail.setText("");
                                editPw.setText("");
                                Toast.makeText(signupMain.this,"회원가입을 축하합니다.",Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e) {}
                        break;
                    case R.id.buttonCancel:
                        finish();
                }

            }
        };


}