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

        buttonTown.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                townMain next = new townMain();
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
    public void changeFragment(int usage) {
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
            default:
                break;
        }
    }
}
