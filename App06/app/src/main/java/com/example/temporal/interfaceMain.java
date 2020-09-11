package com.example.temporal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class interfaceMain extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_main);

        TextView buttonHome = findViewById(R.id.menuHome);
        TextView buttonchallenge = findViewById(R.id.menuchallenge);
        TextView buttonEnroll = findViewById(R.id.menuEnroll);
        TextView buttonInfo = findViewById(R.id.menuInfo);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        homeMain next = new homeMain();
        fragmentTransaction.replace(R.id.frameMain, next);
        fragmentTransaction.commit();

        buttonHome.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                homeMain next = new homeMain();
                fragmentTransaction.replace(R.id.frameMain, next);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        buttonchallenge.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                challengeMain next = new challengeMain();
                fragmentTransaction.replace(R.id.frameMain, next);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        buttonEnroll.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                enrollMain next = new enrollMain();
                fragmentTransaction.replace(R.id.frameMain, next);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        buttonInfo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                infoMain next = new infoMain();
                fragmentTransaction.replace(R.id.frameMain, next);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });
    }

    // 해당 엑티비티 내에서 프레그먼트 바꿀때 사용
    // 백의 자리: 하단 메뉴바 좌측에서부터 1, 2, 3, 4
    // 십 + 일의 자리: 각 메뉴 내부의 프레그먼트
    public void changeFragment(int usage) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (usage) {
            case 100: // 홈 화면 최초 클릭시
                fragmentTransaction.replace(R.id.frameHomeMain, new homeWaste());
                fragmentTransaction.commit();
                break;
            case 200: // 도전과제 최초 클릭시
                fragmentTransaction.replace(R.id.frameChallengeMain, new challengeTown());
                fragmentTransaction.commit();
                break;
            case 210: // 도전과제 -> 리워드
                fragmentTransaction.replace(R.id.frameChallengeMain, new challengeTown());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 220: // 도전과제 -> 펀딩
                fragmentTransaction.replace(R.id.frameChallengeMain, new challengePoint());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 230: // 도전과제 -> 친구리스트
                fragmentTransaction.replace(R.id.frameChallengeMain, new challengeFriend());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 310: // 등록 -> 등록 버튼
                fragmentTransaction.replace(R.id.frameMain, new enrollComplete());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            default:
                break;
        }
    }

    // 홈 프레그먼트의 분리배출법 세부 페이지를 열기 위한 함수
    // parameter: 해당 분리배출법 id(int? String?) <- 데이터베이스
    public void changeFragmentHome(int id) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        homeWasteSpecific newPage = new homeWasteSpecific();
        // 1. id를 이용하여 데이터베이스에서 정보 불러오기
        // 2. 정보 newPage 프레그먼트 변수에 각각 할당
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 등록 프레그먼트의 체크리스트를 불러오기 위한 함수
    // parameter: 아직 생각 안해봄
    public void changeFragmentEnroll() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        enrollChecklist newPage = new enrollChecklist();
        // paremeter를 이용해서 해당 카테고리에 맞는 체크리스트 call
        fragmentTransaction.replace(R.id.frameEnrollMain, newPage);
        fragmentTransaction.commit();

    }
}