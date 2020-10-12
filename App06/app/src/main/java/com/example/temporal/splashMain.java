package com.example.temporal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class splashMain extends AppCompatActivity {

    private retrofitAPI mRetrofitAPI;
    private Retrofit mRetrofit;
    private Call<List<challengeFrameItem>> mCallChallengeframeList;
    private Call<List<fundingItem>> mFundingItemList;
    private Call<List<wasteItem>> mWasteItemList;
    private Call<List<userItem>> mUserItemList;
    private Callback<List<challengeFrameItem>> mFrameCallback = new Callback<List<challengeFrameItem>>() {
        @Override
        public void onResponse(Call<List<challengeFrameItem>> call, Response<List<challengeFrameItem>> response) {
            System.out.println("틀 수신 성공");
            for (challengeFrameItem item :
                    response.body()) {
                challengeItem newOne = new challengeItem();
                newOne.setFromFrame(item);
                aCurrentData.listChallengeEnroll.add(newOne);
            }

        }

        @Override
        public void onFailure(Call<List<challengeFrameItem>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    private Callback<List<userItem>> mUserCallback = new Callback<List<userItem>>() {
        @Override
        public void onResponse(Call<List<userItem>> call, Response<List<userItem>> response) {
            System.out.println("사용자 수신 성공");
            for (userItem item :
                    response.body()) {
                aCurrentData.listUser.add(item);
            }

        }

        @Override
        public void onFailure(Call<List<userItem>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    private Callback<List<fundingItem>> mFundingCallback = new Callback<List<fundingItem>>() {
        @Override
        public void onResponse(Call<List<fundingItem>> call, Response<List<fundingItem>> response) {
            System.out.println("펀딩 수신 성공");
            for (fundingItem item :
                    response.body()) {
                aCurrentData.listFunding.add(item);
            }
        }

        @Override
        public void onFailure(Call<List<fundingItem>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    private Callback<List<wasteItem>> mWasteCallback = new Callback<List<wasteItem>>() {
        @Override
        public void onResponse(Call<List<wasteItem>> call, Response<List<wasteItem>> response) {
            System.out.println("분리배출법 수신 성공");
            for (wasteItem item :
                    response.body()) {
                aCurrentData.listWaste.add(item);
            }
        }

        @Override
        public void onFailure(Call<List<wasteItem>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://101.101.218.146:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(retrofitAPI.class);
    }

    private void callServer() {
        mCallChallengeframeList = mRetrofitAPI.getChallengeframeList();
        mFundingItemList = mRetrofitAPI.getFundingList();
        mWasteItemList = mRetrofitAPI.getWasteList();
        mUserItemList = mRetrofitAPI.getUserList();
        mCallChallengeframeList.enqueue(mFrameCallback);
        mFundingItemList.enqueue(mFundingCallback);
        mWasteItemList.enqueue(mWasteCallback);
        mUserItemList.enqueue(mUserCallback);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);
        setRetrofitInit();
        callServer();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash = new Intent(splashMain.this, MainActivity.class);
                startActivity(splash);
                finish();
            }
        }, 1500);
    }
}