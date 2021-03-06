package com.example.temporal;

import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fundingList extends Fragment implements OnItemClickForFunding {
    RecyclerView viewList;
    fundingItemAdapter adapter;
    private retrofitAPI mRetrofitAPI;
    private Retrofit mRetrofit;
    Call<Boolean> mPostFundingActivity;
    Call<Boolean> mchangePoint;
    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://101.101.218.146:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(retrofitAPI.class);
    }
    Callback<Boolean> fundingItemCallback = new Callback<Boolean>() {
        @Override
        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
            System.out.println("Post 전송 성공");
            System.out.println(response);
            System.out.println(call);
        }

        @Override
        public void onFailure(Call<Boolean> call, Throwable t) {
            System.out.println("전송 실패");
            t.printStackTrace();
        }
    };
    Callback<point> fundingPointCallback = new Callback<point>() {
        @Override
        public void onResponse(Call<point> call, Response<point> response) {
            System.out.println("Put 전송 성공");
            System.out.println(response);
            System.out.println(call);
        }

        @Override
        public void onFailure(Call<point> call, Throwable t) {
            System.out.println("전송 실패");
            t.printStackTrace();
        }
    };

    //    ConstraintLayout funding_list = viewList.findViewById(R.id.funding_list);
//    TextView funding = viewList.findViewById(R.id.funding);
//    TextView fundingTitle = viewList.findViewById(R.id.fundingTitle);
//    TextView fundingSpecific = viewList.findViewById(R.id.fundingSpecific);
//    ConstraintLayout funding_click = viewList.findViewById(R.id.funding_click);
    TextView pointSpent;
    TextView pointRest;
    TextView myPoint;
    TextView acu_point;
    TextView fund_point;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.funding_list, container,false);

        setRetrofitInit();

        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        adapter = new fundingItemAdapter(aCurrentData.listFunding, this);
        viewList.setAdapter(adapter);

        return view;

    }

    @Override
    public void onClick(fundingItem newOne) {
        ConstraintLayout funding_list = viewList.findViewById(R.id.funding_list);

        TextView funding = viewList.findViewById(R.id.funding);
        TextView inst_desc = viewList.findViewById(R.id.inst_desc);

        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.funding_translate_top);   // 에니메이션 설정 파일
        funding_list.startAnimation(anim);

        inst_desc.setVisibility(View.GONE);
        funding.setVisibility(View.GONE);

        funding_list.setVisibility(View.VISIBLE);

        funding_list.setBackgroundColor(Color.parseColor("#79C3A0"));
    }

    @Override
    public void onScroll(fundingItem newOne) {

        TextView textNowPoint = viewList.findViewById(R.id.textNowPoint);
        TextView textPoint = viewList.findViewById(R.id.textPoint);
        TextView textRestPoint = viewList.findViewById(R.id.textRestPoint);

        textNowPoint.setVisibility(View.VISIBLE);
        textPoint.setVisibility(View.VISIBLE);
        textRestPoint.setVisibility(View.VISIBLE);

        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.funding_translate_top);   // 에니메이션 설정 파일
        viewList.startAnimation(anim);
    }

    @Override
    public void onFundingClick(fundingItem newOne) {
        ConstraintLayout funding_list = viewList.findViewById(R.id.funding_list);
        ConstraintLayout funding_click = viewList.findViewById(R.id.funding_click);
        pointSpent = viewList.findViewById(R.id.fund_point);
        pointRest = viewList.findViewById(R.id.rest_point);
        myPoint = viewList.findViewById(R.id.point);
        acu_point = viewList.findViewById(R.id.acu_point);
        fund_point = viewList.findViewById(R.id.fund_point);

        int accu = Integer.parseInt((acu_point.getText().toString())) + Integer.parseInt((pointSpent.getText().toString()));
        newOne.acu_point = accu;

        fundingItemActivity newActivity = new fundingItemActivity();
        newActivity.fund_id = newOne.fund_id;
        newActivity.mem_id = aCurrentData.myInfo.id;
        newActivity.point = Integer.parseInt((pointSpent.getText().toString()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        newActivity.funddate = sdf.format(Calendar.getInstance().getTime());
        newActivity.acu_point = accu;

        aCurrentData.myInfo.point = Integer.parseInt((pointRest.getText().toString()));
        point newpointActivity = new point();
        newpointActivity.point = Integer.parseInt((pointRest.getText().toString()));

        mPostFundingActivity = mRetrofitAPI.postFundingActivity(newActivity);
        mchangePoint = mRetrofitAPI.changePoint(aCurrentData.myInfo.id, newpointActivity);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mPostFundingActivity.execute();
                    mchangePoint.execute();
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

        funding_list.setVisibility(View.GONE);
        funding_click.setVisibility(View.VISIBLE);
        funding_click.setBackgroundColor(Color.parseColor("#79C3A0"));

    }

}

