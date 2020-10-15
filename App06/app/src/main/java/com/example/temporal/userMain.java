package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class userMain extends Fragment implements OnItemClickForInfo {
    RecyclerView viewList;
    userActivityAdapter adapter;
    TextView textNick;

    private retrofitAPI mRetrofitAPI;
    private Retrofit mRetrofit;
    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://101.101.218.146:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(retrofitAPI.class);
    }

    private Call<List<challengeItemActivityForGet>> mCallChallengeActivity;
    private Call<List<fundingItemActivityForGet>> mCallFundingActivity;
    private Call<List<challengeItem>> mCallChallenge;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_main, container, false);
        setRetrofitInit();
        mCallChallengeActivity = mRetrofitAPI.getChallengeActivityListByUser(aCurrentData.myInfo.id);
        mCallFundingActivity = mRetrofitAPI.getFundingActivityListByUser(aCurrentData.myInfo.id);
        mCallChallenge = mRetrofitAPI.getCompletedChallengeList(aCurrentData.myInfo.id);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                aCurrentData.listActivity.clear();
                try {
                    for (challengeItemActivityForGet item : mCallChallengeActivity.execute().body()) {
                        userActivity newOne = new userActivity();
                        newOne.setFromChallengeActivity(item);
                        aCurrentData.listActivity.add(newOne);
                    }
                    for (challengeItem item : mCallChallenge.execute().body()) {
                        userActivity newOne = new userActivity();
                        newOne.setFromChallengeItem(item);
                        aCurrentData.listActivity.add(newOne);
                    }
                    for (fundingItemActivityForGet item : mCallFundingActivity.execute().body()) {
                        userActivity newOne = new userActivity();
                        newOne.setFromFundingActivity(item);
                        aCurrentData.listActivity.add(newOne);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collections.sort(aCurrentData.listActivity, new Comparator<userActivity>() {
            @Override
            public int compare(userActivity userActivity, userActivity t1) {
                return userActivity.date.compareTo(t1.date);
            }
        });

        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new userActivityAdapter(aCurrentData.listActivity, this);
        viewList.setAdapter(adapter);

        textNick = view.findViewById(R.id.user);
        textNick.setText(aCurrentData.myInfo.nick);

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }

    @Override
    public void onClick(userActivity newOne) {

    }
}