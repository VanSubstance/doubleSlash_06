package com.example.temporal;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class homeMain extends Fragment implements OnItemClickForChallenge {
    RecyclerView viewList2;
    wasteItemBannerAdapter adapter2;
    RecyclerView viewList;
    challengeItemAdapter adapter;
    ArrayList<challengeItem> items = new ArrayList<challengeItem>();

    private retrofitAPI mRetrofitAPI;
    private Retrofit mRetrofit;
    private Call<List<challengeItemActivityForGet>> mCallActivities;
    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://101.101.218.146:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(retrofitAPI.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_main, container, false);
        setRetrofitInit();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(challengeItem one : aCurrentData.listMyChallenge) {
                    mCallActivities = mRetrofitAPI.getChallengeActivityList(one.chalId);
                    List<challengeItemActivityForGet> original = null;
                    try {
                        original = mCallActivities.execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (original == null || original.size() == 0) {
                    } else {
                        one.progress = original.size();
                        int emptyLength = one.acvts.size() - original.size();
                        one.acvts.clear();
                        for (challengeItemActivityForGet items : original) {
                            challengeItemActivity newOne = new challengeItemActivity();
                            newOne.setFromDb(items);
                            one.acvts.add(newOne);
                        }
                        for (int i = 0; i < emptyLength; i++) {
                            challengeItemActivity newOne = new challengeItemActivity();
                            one.acvts.add(newOne);
                        }
                    }
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (challengeItem it : aCurrentData.listMyChallenge) {
            if (items.size() < 3) {
                items.add(it);
            } else {
                for (challengeItem currentOne : items) {
                    if (currentOne.progress < it.progress) {
                        items.remove(currentOne);
                        items.add(it);
                        break;
                    }
                }
            }
        }
        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);
        // 좌우 넘기기
        adapter = new challengeItemAdapter(items, this);
        viewList.setAdapter(adapter);

        viewList2 = view.findViewById(R.id.recyclerView2);
        viewList2.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        // 부드럽게 넘기기
        snapHelper.attachToRecyclerView(viewList2);
        adapter2 = new wasteItemBannerAdapter(aCurrentData.listWasteBanner);
        viewList2.setAdapter(adapter2);

        ConstraintLayout buttonMask = view.findViewById(R.id.buttonMask);
        ConstraintLayout buttonPlastic = view.findViewById(R.id.buttonPlastic);
        ConstraintLayout buttonPaper = view.findViewById(R.id.buttonPaper);
        ConstraintLayout buttonCan = view.findViewById(R.id.buttonCan);
        ConstraintLayout buttonVinyl = view.findViewById(R.id.buttonVinyl);
        ConstraintLayout buttonEtc = view.findViewById(R.id.buttonEtc);

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 데이터베이스에서 ctgr 맞는거 불러오기
                switch (view.getId()) {
                    case R.id.buttonMask:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("마스크");
                        break;
                    case R.id.buttonPlastic:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("플라스틱");
                        break;
                    case R.id.buttonPaper:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("종이");
                        break;
                    case R.id.buttonCan:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("유리");
                        break;
                    case R.id.buttonVinyl:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("비닐");
                        break;
                    case R.id.buttonEtc:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("기타");
                        break;
                }
            }
        };

        buttonMask.setOnClickListener(btnListener);
        buttonPlastic.setOnClickListener(btnListener);
        buttonPaper.setOnClickListener(btnListener);
        buttonCan.setOnClickListener(btnListener);
        buttonVinyl.setOnClickListener(btnListener);
        buttonEtc.setOnClickListener(btnListener);
        return view;
    }

    @Override
    public void onClick(challengeItem newOne) {
        ((interfaceMain)getActivity()).changeFragmentChallengeItemSpecific(newOne);
    }
}