package com.example.temporal;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener;
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class challengeEnrollItemSpecific extends Fragment {
    private retrofitAPI mRetrofitAPI;
    private Retrofit mRetrofit;
    challengeItem item = new challengeItem();
    CalendarView calendarView;
    TextView textPointTotal;
    public void setItem(challengeItem newOne) {
        item.clone(newOne);
    }
    private Call<List<challengeItem>> mChallengeItemList;
    Call<challengeItemForPost> newChallenge;
    Callback<challengeItemForPost> mChallengePostCallback = new Callback<challengeItemForPost>() {
        @Override
        public void onResponse(Call<challengeItemForPost> call, Response<challengeItemForPost> response) {
            System.out.println("전송 성공");
            System.out.println(response);
            System.out.println(call);
        }
        @Override
        public void onFailure(Call<challengeItemForPost> call, Throwable t) {
            System.out.println("전송 실패");
            t.printStackTrace();
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetrofitInit();
        View view = inflater.inflate(R.layout.challenge_enroll_item_specific, container,false);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textDescription = view.findViewById(R.id.textDescription);
        TextView textPointDay = view.findViewById(R.id.textPointDay);
        textPointTotal = view.findViewById(R.id.textPointTotal);
        textPointDay.setText(String.valueOf(item.chalPoint));
        textTitle.setText(item.title);
        textDescription.setText(item.des);
        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setSelectionType(SelectionType.RANGE);
        calendarView.getConnectedDaysManager().setConnectedDaysList(null);
        calendarView.setWeekendDayTextColor(Color.RED);
        calendarView.setSelectionManager(new RangeSelectionManager(new OnDaySelectedListener() {
            @Override
            public void onDaySelected() {
                textPointTotal.setText(String.valueOf(item.chalPoint * calendarView.getSelectedDates().size()));
            }
        }));
        Button buttonEnroll = view.findViewById(R.id.buttonEnroll);
        buttonEnroll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                challengeItemForPost itemPost = new challengeItemForPost();
                item.setDates(calendarView.getSelectedDates());
                itemPost.setDates(calendarView.getSelectedDates());
                itemPost.memId = aCurrentData.myInfo.id;
                itemPost.chalfrId = item.chalId;
                item.chalPoint = item.chalPoint * calendarView.getSelectedDates().size();
                itemPost.chalPoint = item.chalPoint;
                aCurrentData.listMyChallenge.add(item);
                newChallenge = mRetrofitAPI.setChallenge(itemPost);
                newChallenge.enqueue(mChallengePostCallback);
                mChallengeItemList = mRetrofitAPI.getChallengeList(aCurrentData.myInfo.id);
                mChallengeItemList.enqueue(new Callback<List<challengeItem>>() {
                    @Override
                    public void onResponse(Call<List<challengeItem>> call, Response<List<challengeItem>> response) {
                        System.out.println("챌린지 수신 성공");
                        for (challengeItem item :
                                response.body()) {
                            item.setDatesFromServer();
                            aCurrentData.listMyChallenge.add(item);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<challengeItem>> call, Throwable t) {
                        System.out.println("챌린지 수신 실패");
                        t.printStackTrace();
                    }
                });
                ((interfaceMain)getActivity()).callMenu(R.id.menuHome);
            }
        });

        return view;
    }

    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://101.101.218.146:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(retrofitAPI.class);
    }

}
