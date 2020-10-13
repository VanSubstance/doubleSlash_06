package com.example.temporal;


import android.app.Fragment;

import android.content.Intent;

import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class challengeItemSpecific extends Fragment {
    private retrofitAPI mRetrofitAPI;
    private Retrofit mRetrofit;
    private int num;
    private ImageView[] newActivity;
    private Call<challengeItemActivity> mChallengeItemActivity;
    private Callback<challengeItemActivity> challengeItemActivityCallback = new Callback<challengeItemActivity>() {
        @Override
        public void onResponse(Call<challengeItemActivity> call, Response<challengeItemActivity> response) {
            System.out.println("챌린지 활동 등록 성공");
            System.out.println(response);
            System.out.println(call);
        }

        @Override
        public void onFailure(Call<challengeItemActivity> call, Throwable t) {
            System.out.println("사용자 위치 수정 실패");
            System.out.println(call);
            t.printStackTrace();
        }
    };
    private Call<List<challengeItemActivity>> mCallAvtivities;
    private Callback<List<challengeItemActivity>> activitiesCallback = new Callback<List<challengeItemActivity>>() {
        @Override
        public void onResponse(Call<List<challengeItemActivity>> call, Response<List<challengeItemActivity>> response) {
            item.acvts.clear();
            for (challengeItemActivity items : response.body()) {
                challengeItemActivity newOne = new challengeItemActivity();
                newOne.clone(items);
                item.acvts.add(newOne);
            }

        }

        @Override
        public void onFailure(Call<List<challengeItemActivity>> call, Throwable t) {

        }
    };
    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://101.101.218.146:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(retrofitAPI.class);
    }

    challengeItem item = new challengeItem();
    CalendarView calendarView;
    TextView textPointTotal;


    public void setItem(challengeItem newOne) {
        item.clone(newOne);
        /*
        mCallAvtivities = mRetrofitAPI.getChallengeActivityList(aCurrentData.myInfo.id);
        mCallAvtivities.enqueue(activitiesCallback);

         */
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_item_specific, container,false);
        setRetrofitInit();

        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textDescription = view.findViewById(R.id.textDescription);
        textTitle.setText(item.title);
        textDescription.setText(item.des);
        textPointTotal = view.findViewById(R.id.textPointTotal);
        textPointTotal.setText(String.valueOf(item.chalPoint));

        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setSelectionType(SelectionType.NONE);
        calendarView.setWeekendDayTextColor(Color.RED);
        int colorChosen = Color.parseColor("#0096c6");
        calendarView.getConnectedDaysManager().setConnectedDaysList(null);
        calendarView.addConnectedDays(new ConnectedDays(item.days, colorChosen, Color.YELLOW, Color.WHITE));
        calendarView.getConnectedDaysManager().setConnectedDaysList(null);

        LinearLayout layoutImages = view.findViewById(R.id.layoutImages);
        LinearLayout rowImages = null;
        LinearLayout.LayoutParams settingRow = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        settingRow.setMargins(0, 40, 0 ,0);



        View.OnClickListener myListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tvKey=(Integer)view.getTag();
                challengeItemActivity postOne = new challengeItemActivity();
                postOne.img = "0";
                postOne.chalId = item.chalId;
                mChallengeItemActivity = mRetrofitAPI.postChallengeActivity(postOne);
                mChallengeItemActivity.enqueue(challengeItemActivityCallback);
                item.acvts.get(tvKey).img = "0";
                item.acvts.get(tvKey).chalId = item.chalId;
                newActivity[tvKey].setImageResource(R.drawable.image_enrolled);
            }
        };

        newActivity= new ImageView[item.acvts.size()];
        // 이미지 동적 생성
        for (int i = 0; i < item.acvts.size(); i++) {
            num=i;
            if ( i % 3 == 0) {
                rowImages = new LinearLayout(this.getContext());
                rowImages.setOrientation(LinearLayout.HORIZONTAL);
                rowImages.setLayoutParams(settingRow);
            }
            newActivity[num]=new ImageView(this.getContext());
            newActivity[num].setTag(num);
            LinearLayout slotActivity = new LinearLayout(this.getContext());
            slotActivity.setOrientation(LinearLayout.HORIZONTAL);
            // 엑티비티 존재할 경우
            if (item.acvts.get(num).img == "0") {
                newActivity[num].setImageResource(R.drawable.image_enrolled);
            } else {
                newActivity[num].setImageResource(R.drawable.image_default);
                newActivity[num].setOnClickListener(myListener);
            }
            LinearLayout.LayoutParams settingImage = new LinearLayout.LayoutParams(400, 400);
            settingImage.setMargins(10, 0, 10, 0);
            newActivity[num].setLayoutParams(settingImage);
            newActivity[num].setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams settingSlot = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            settingSlot.weight = 1;
            settingSlot.gravity = Gravity.CENTER;
            slotActivity.setLayoutParams(settingSlot);
            slotActivity.addView(newActivity[num]);
            rowImages.addView(slotActivity);
            if (i % 3 == 2 || i + 1 == item.acvts.size()) {
                layoutImages.addView(rowImages);
            }
        }

        return view;
    }

  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            newActivity.setImageURI(selectedImageUri);
            // image.url=selectedImageUri.toString();
        }
    }*/
}
