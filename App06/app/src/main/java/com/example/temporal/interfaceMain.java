package com.example.temporal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class interfaceMain extends AppCompatActivity {
    FragmentManager fragmentManager = getFragmentManager();

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

        // 데이터베이스에서 ctgr 맞는거 불러오기
        aCurrentData.listWasteBanner.clear();
        for (int i = 0; i < 3; i++) {
            wasteItemBanner newOne = new wasteItemBanner();
            switch (i) {
                case 0:
                    newOne.img = R.drawable.banner01;
                    break;
                case 1:
                    newOne.img = R.drawable.banner02;
                    break;
                case 2:
                    newOne.img = R.drawable.banner03;
                    break;
            }
            aCurrentData.listWasteBanner.add(newOne);
        }
        // 잡다한 변수들
        {
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
            callMenu(R.id.menuHome);

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
                fragmentTransaction.replace(R.id.frameMain, new userMain());
                break;
        }
        fragmentTransaction.commit();

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
            default:
                break;
        }
    }

    public void changeFragmentWasteCtgr(String ctgr) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        wasteMain newPage = new wasteMain();
        newPage.setCtgr(ctgr);
        ArrayList<wasteItem> newOne = new ArrayList<wasteItem>();
        switch (ctgr) {
            case "유리":
                wasteItem newWaste = new wasteItem();
                newWaste.img = R.drawable.glass01;
                newOne.add(newWaste);
                break;
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

}
