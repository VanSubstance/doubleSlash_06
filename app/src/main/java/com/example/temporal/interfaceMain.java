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
        TextView buttonTown = findViewById(R.id.menuTown);
        TextView buttonEnroll = findViewById(R.id.menuEnroll);
        TextView buttonInfo = findViewById(R.id.menuInfo);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        homeMain next = new homeMain();
        fragmentTransaction.replace(R.id.mainFrame, next);
        fragmentTransaction.commit();

        buttonHome.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                homeMain next = new homeMain();
                fragmentTransaction.replace(R.id.mainFrame, next);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        buttonTown.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                challengeMain next = new challengeMain();
                fragmentTransaction.replace(R.id.mainFrame, next);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        buttonEnroll.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                enrollMain next = new enrollMain();
                fragmentTransaction.replace(R.id.mainFrame, next);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        buttonInfo.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                infoMain next = new infoMain();
                fragmentTransaction.replace(R.id.mainFrame, next);
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
            case 1:
                /**
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                communityWrite next = new communityWrite();
                fragmentTransaction.replace(R.id.frameMain, next);
                fragmentTransaction.addToBackStack(null).commit();
                 */
                break;
            case 200: // 도전과제 최초 클릭시
                fragmentTransaction.replace(R.id.townMainFrame, new challengeTown());
                fragmentTransaction.commit();
                break;
            case 210: // 도전과제 -> 리워드
                fragmentTransaction.replace(R.id.townMainFrame, new challengeTown());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 220: // 도전과제 -> 펀딩
                fragmentTransaction.replace(R.id.townMainFrame, new challengePoint());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 230: // 도전과제 -> 친구리스트
                fragmentTransaction.replace(R.id.townMainFrame, new challengeFriend());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            default:
                break;
        }
    }
}
