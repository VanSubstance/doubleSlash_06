package com.example.temporal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class interfaceMain extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_main);

        TextView buttonHome = findViewById(R.id.menuHome);
        TextView buttonChallenge = findViewById(R.id.menuChallenge);
        TextView buttonFunding = findViewById(R.id.menuFunding);
        TextView buttonList = findViewById(R.id.menuList);
        TextView buttonInfo = findViewById(R.id.menuInfo);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        homeMain next = new homeMain();
        fragmentTransaction.replace(R.id.frameMain, next);
        fragmentTransaction.commit();

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (view.getId()) {
                    case R.id.menuHome:
                        fragmentTransaction.replace(R.id.frameMain, new homeMain());
                        break;
                    case R.id.menuChallenge:
                        fragmentTransaction.replace(R.id.frameMain, new challengeMain());
                        break;
                    case R.id.menuList:
                        fragmentTransaction.replace(R.id.frameMain, new listMain());
                        break;
                    case R.id.menuFunding:
                        fragmentTransaction.replace(R.id.frameMain, new fundingMain());
                        break;
                    case R.id.menuInfo:
                        fragmentTransaction.replace(R.id.frameMain, new infoMain());
                        break;
                }
                fragmentTransaction.addToBackStack(null).commit();
            }
        };
        buttonHome.setOnClickListener(btnListener);
        buttonChallenge.setOnClickListener(btnListener);
        buttonList.setOnClickListener(btnListener);
        buttonFunding.setOnClickListener(btnListener);
        buttonInfo.setOnClickListener(btnListener);
    }

    // 해당 엑티비티 내에서 프레그먼트 바꿀때 사용
    // 백의 자리: 하단 메뉴바 좌측에서부터 1, 2, 3, 4
    // 십 + 일의 자리: 각 메뉴 내부의 프레그먼트
    public void changeFragment(int usage) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (usage) {
            case 100: // 홈 화면 최초 클릭시
                break;
            case 200: // 챌린지 화면 최초 클릭시
                fragmentTransaction.replace(R.id.frameChallengeMap, new challengeMap());
                fragmentTransaction.commit();
                break;
            case 300: // 펀딩 화면 최초 클릭시
                fragmentTransaction.replace(R.id.frameFundingList, new fundingList());
                fragmentTransaction.commit();
                changeFragmentFundingList();
                break;
            case 310: // 펀딩하기 버튼 클릭시
                fragmentTransaction.replace(R.id.frameMain, new fundingList());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 310: // 챌린지 목록 -> 추가버튼 클릭시
                fragmentTransaction.replace(R.id.frameMain, new listEnroll());
                fragmentTransaction.commit();
                break;

            case 410: // 등록 -> 등록 버튼
                fragmentTransaction.replace(R.id.frameMain, new enrollComplete());
                fragmentTransaction.addToBackStack(null).commit();
                break;

            default:
                break;
        }
    }

    public void changeFragmentChallengeEnrollList() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeEnrollList newPage = new challengeEnrollList();
        fragmentTransaction.replace(R.id.frameChallengeList, newPage);
        fragmentTransaction.commit();
    }
    // 홈의 분리배출법 리스트 불러오기
    // parameter : 스피너 값
    public void changeFragmentWasteList() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        wasteList newPage = new wasteList();
        fragmentTransaction.replace(R.id.frameHomeWasteList, newPage);
        fragmentTransaction.commit();
    }

    public void changeFragmentChallengeList() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeList newPage = new challengeList();
        fragmentTransaction.replace(R.id.frameChallengeList, newPage);
        fragmentTransaction.commit();
    }

    // 펀딩 리스트 불러오기
    // parameter : 스피너 값
    public void changeFragmentFundingList() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fundingList newPage = new fundingList();
        fragmentTransaction.replace(R.id.frameFundingList, newPage);
        fragmentTransaction.commit();
    }

    // 분리배출법 세부페이지
    public void changeFragmentWasteItemSpecific(wasteItem newOne) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        wasteItemSpecific newPage = new wasteItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

//    // 펀딩 세부
//    public void changeFragmentFundingItemSpecific(fundingItem newOne) {
//        TextView txt = (TextView) findViewById(R.id.textDescription);
//        txt.setText(btn.getText());
//
//    }

    // 펀딩 세부페이지
    public void changeFragmentFundingItemSpecific(fundingItem newOne) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fundingItemSpecific newPage = new fundingItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 챌린지 세부페이지
    public void changeFragmentChallengeItemSpecific(challengeItem newOne) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeItemSpecific newPage = new challengeItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

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
     fragmentTransaction.commit();
     }
     */

}
