package com.example.temporal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class interfaceMain extends AppCompatActivity {
    FragmentManager fragmentManager = getFragmentManager();
    private Retrofit mRetrofit;
    private retrofitAPI mRetrofitAPI;
    private Call<List<challengeFrameItem>> mCallChallengeframeList;
    private Call<List<fundingItem>> mFundingItemList;
    private Call<List<wasteItem>> mWasteItemList;
    private Gson mGson;

    private Callback<List<challengeFrameItem>> mFrameCallback = new Callback<List<challengeFrameItem>>() {
        @Override
        public void onResponse(Call<List<challengeFrameItem>> call, Response<List<challengeFrameItem>> response) {
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

    private Callback<List<fundingItem>> mFundingCallback = new Callback<List<fundingItem>>() {
        @Override
        public void onResponse(Call<List<fundingItem>> call, Response<List<fundingItem>> response) {
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

    ImageView imageHome;
    ImageView imageChallenge;
    ImageView imageFunding;
    ImageView imageEnroll;
    ImageView imageInfo;

    TextView textHome;
    TextView textChallenge;
    TextView textFunding;
    TextView textEnroll;
    TextView textInfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_main);

        setRetrofitInit();
        callChallengeframeList();

        // 데이터베이스에서 ctgr 맞는거 불러오기
        aCurrentData.listWasteBanner.clear();
        for (int i = 0; i < 3; i++) {
            wasteItemBanner newOne = new wasteItemBanner();
            newOne.init(i);
            aCurrentData.listWasteBanner.add(newOne);
        }

        ConstraintLayout buttonHome = findViewById(R.id.menuHome);
        ConstraintLayout buttonChallenge = findViewById(R.id.menuChallenge);
        ConstraintLayout buttonFunding = findViewById(R.id.menuFunding);
        ConstraintLayout buttonEnroll = findViewById(R.id.menuEnroll);
        ConstraintLayout buttonInfo = findViewById(R.id.menuInfo);

        imageHome = findViewById(R.id.imageHome);
        imageChallenge = findViewById(R.id.imageChallenge);
        imageFunding = findViewById(R.id.imageFunding);
        imageEnroll = findViewById(R.id.imageEnroll);
        imageInfo = findViewById(R.id.imageInfo);

        textHome = findViewById(R.id.textHome);
        textChallenge = findViewById(R.id.textChallenge);
        textFunding = findViewById(R.id.textFunding);
        textEnroll = findViewById(R.id.textEnroll);
        textInfo = findViewById(R.id.textInfo);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        homeMain next = new homeMain();
        fragmentTransaction.replace(R.id.frameMain, next);
        fragmentTransaction.addToBackStack(null).commit();

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callMenu(view.getId());
            }
        };
        buttonHome.setOnClickListener(btnListener);
        buttonChallenge.setOnClickListener(btnListener);
        buttonEnroll.setOnClickListener(btnListener);
        buttonFunding.setOnClickListener(btnListener);
        buttonInfo.setOnClickListener(btnListener);
    }

    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://101.101.218.146:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(retrofitAPI.class);
    }

    private void callChallengeframeList() {

        mCallChallengeframeList = mRetrofitAPI.getChallengeframeList();
        mFundingItemList = mRetrofitAPI.getFundingList();
        mWasteItemList = mRetrofitAPI.getWasteList();

        mCallChallengeframeList.enqueue(mFrameCallback);
        mFundingItemList.enqueue(mFundingCallback);
        mWasteItemList.enqueue(mWasteCallback);
        
    }

    public void clearMenu() {
        imageHome.setImageResource(R.drawable.home_default);
        textHome.setTextColor(Color.parseColor("#EEEEEE"));
        imageChallenge.setImageResource(R.drawable.map_default);
        textChallenge.setTextColor(Color.parseColor("#EEEEEE"));
        imageEnroll.setImageResource(R.drawable.enroll_default);
        textEnroll.setTextColor(Color.parseColor("#EEEEEE"));
        imageFunding.setImageResource(R.drawable.funding_default);
        textFunding.setTextColor(Color.parseColor("#EEEEEE"));
        imageInfo.setImageResource(R.drawable.my_default);
        textInfo.setTextColor(Color.parseColor("#EEEEEE"));
    }

    public void callMenu(int id) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id) {
            case R.id.menuHome:
                clearMenu();
                imageHome.setImageResource(R.drawable.home_on);
                textHome.setTextColor(Color.parseColor("#6A6A6A"));
                fragmentTransaction.replace(R.id.frameMain, new homeMain());
                break;
            case R.id.menuChallenge:
                clearMenu();
                imageChallenge.setImageResource(R.drawable.map_on);
                textChallenge.setTextColor(Color.parseColor("#6A6A6A"));
                fragmentTransaction.replace(R.id.frameMain, new challengeMain());
                break;
            case R.id.menuEnroll:
                clearMenu();
                imageEnroll.setImageResource(R.drawable.enroll_on);
                textEnroll.setTextColor(Color.parseColor("#6A6A6A"));
                fragmentTransaction.replace(R.id.frameMain, new listEnroll());
                break;
            case R.id.menuFunding:
                clearMenu();
                imageFunding.setImageResource(R.drawable.funding_on);
                textFunding.setTextColor(Color.parseColor("#6A6A6A"));
                fragmentTransaction.replace(R.id.frameMain, new fundingMain());
                break;
            case R.id.menuInfo:
                clearMenu();
                imageInfo.setImageResource(R.drawable.my_on);
                textInfo.setTextColor(Color.parseColor("#6A6A6A"));
                fragmentTransaction.replace(R.id.frameMain, new infoMain());
                break;
        }
        fragmentTransaction.addToBackStack(null).commit();

    }

    // 해당 엑티비티 내에서 프레그먼트 바꿀때 사용
    // 백의 자리: 하단 메뉴바 좌측에서부터 1, 2, 3, 4
    // 십 + 일의 자리: 각 메뉴 내부의 프레그먼트
    public void changeFragment(int usage) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (usage) {
            case 110: // 메인 챌린지 더보기 버튼 클릭시
                fragmentTransaction.replace(R.id.frameMain, new listMain());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 400: // 펀딩 화면 최초 클릭시
                fragmentTransaction.replace(R.id.frameFundingList, new fundingList());
                fragmentTransaction.addToBackStack(null).commit();
                changeFragmentFundingList();
                break;
            case 500: // 마이페이지 화면 최초 클릭시
                fragmentTransaction.replace(R.id.frameInfoList, new infoList());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 510: // 마이페이지 즐겨찾기 챌린지 목록
                fragmentTransaction.replace(R.id.frameMain, new infoChallengeEnrollList());
                fragmentTransaction.addToBackStack(null).commit();
            default:
                break;
        }
    }

    public void changeFragmentWasteCtgr(String ctgr) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        wasteMain newPage = new wasteMain();
        ArrayList<wasteItem> newOne = new ArrayList<wasteItem>();
        for (int i = 0; i < aCurrentData.listWaste.size(); i++) {
            if (aCurrentData.listWaste.get(i).ctgr.equals(ctgr)) {
                newOne.add(aCurrentData.listWaste.get(i));
            }
        }
        newPage.setItems(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 펀딩 리스트 불러오기
    // parameter : 스피너 값
    public void changeFragmentFundingList() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fundingList newPage = new fundingList();
        fragmentTransaction.replace(R.id.frameFundingList, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 챌린지 세부페이지 내꺼
    public void changeFragmentChallengeItemSpecific(challengeItem newOne) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeItemSpecific newPage = new challengeItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    public void changeFragmentChallengeComplete(challengeItem newOne) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeComplete newPage = new challengeComplete();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 챌린지 등록 세부페이지 내꺼
    public void changeFragmentChallengeEnrollItemSpecific(challengeItem newOne) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeEnrollItemSpecific newPage = new challengeEnrollItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 챌린지 세부페이지 남의 것
    public void changeFragmentChallengeItemSpecificOther(challengeItem newOne) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeItemSpecificOther newPage = new challengeItemSpecificOther();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 마이페이지
//    public void changeFragmentInfoList() {
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        infoList newPage = new infoList();
//        fragmentTransaction.replace(R.id.frameInfoList, newPage);
//        fragmentTransaction.addToBackStack(null).commit();
//    }

    /** 사용 안함
     // 등록 프레그먼트의 체크리스트를 불러오기 위한 함수
     // parameter: 아직 생각 안해봄
     public void changeFragmentEnroll(enrollChecklistItem item) {
     FragmentManager fragmentManager = getFragmentManager();
     FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
     enrollChecklist newPage = new enrollChecklist();
     Bundle bd = new Bundle(2);
     bd.putString("ctgr", item.ctgr);
     bd.putStringArrayList("seq", item.seq);
     newPage.setArguments(bd);
     // paremeter를 이용해서 해당 카테고리에 맞는 체크리스트 call
     fragmentTransaction.replace(R.id.frameEnrollMain, newPage);
     fragmentTransaction.addToBackStack(null).commit();
     }
     */

}
