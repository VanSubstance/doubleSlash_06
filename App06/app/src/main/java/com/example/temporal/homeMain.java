package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class homeMain extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_main, container, false);
        // 맞는 챌린지 불러오기
        aCurrentData.listChallenge.clear();
        for (int i = 0; i < 3; i++) {
            challengeItem newOne = new challengeItem();
            newOne.init(i, "메인");
            aCurrentData.listChallenge.add(newOne);
        }
        ((interfaceMain)getActivity()).changeFragmentChallengeList();

        // 데이터베이스에서 ctgr 맞는거 불러오기
        aCurrentData.listWaste.clear();
        for (int i = 0; i < 10; i++) {
            wasteItem newOne = new wasteItem();
            newOne.init(i);
            newOne.setCtgr("마스크");
            aCurrentData.listWaste.add(newOne);
        }
        ((interfaceMain)getActivity()).changeFragmentWasteList();

        final ScrollView scrollView = view.findViewById(R.id.viewScroll);
        final Button buttonDownward = view.findViewById(R.id.buttonDownward);
        final Button buttonUpward = view.findViewById(R.id.buttonUpward);
        buttonUpward.setVisibility(View.GONE);
        buttonDownward.setVisibility(View.VISIBLE);
        buttonDownward.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.fullScroll(View.FOCUS_DOWN);
                buttonDownward.setVisibility(View.GONE);
                buttonUpward.setVisibility(View.VISIBLE);
            }
        });
        buttonUpward.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.fullScroll(View.FOCUS_UP);
                buttonUpward.setVisibility(View.GONE);
                buttonDownward.setVisibility(View.VISIBLE);
            }
        });
        scrollView.setOnTouchListener(new ScrollView.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (scrollView.getScrollY() >= 0 && scrollView.getScrollY() < 400) {
                    buttonDownward.setVisibility(View.VISIBLE);
                    buttonUpward.setVisibility(View.GONE);
                } else {
                    buttonUpward.setVisibility(View.VISIBLE);
                    buttonDownward.setVisibility(View.GONE);
                }
                return false;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 스크롤 위치 파악해서 버튼 보여주거나 안보여주거나 선언
                }
            }
        }).start();

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
                for (wasteItem item : aCurrentData.listWaste) {
                    switch (view.getId()) {
                        case R.id.buttonMask:
                            item.setCtgr("마스크");
                            break;
                        case R.id.buttonPlastic:
                            item.setCtgr("플라스틱");
                            break;
                        case R.id.buttonPaper:
                            item.setCtgr("종이");
                            break;
                        case R.id.buttonCan:
                            item.setCtgr("캔/유리");
                            break;
                        case R.id.buttonVinyl:
                            item.setCtgr("비닐");
                            break;
                        case R.id.buttonEtc:
                            item.setCtgr("기타");
                            break;
                    }
                }
                ((interfaceMain)getActivity()).changeFragmentWasteList();
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

}