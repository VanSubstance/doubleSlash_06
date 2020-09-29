package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

public class challengeMain extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.challenge_main, container, false);
        ((interfaceMain) getActivity()).changeFragment(200);
        // 맞는 챌린지 불러오기
        aCurrentData.listChallenge.clear();
        for (int i = 0; i < 4; i++) {
            challengeItem newOne = new challengeItem();
            newOne.init(i, "챌린지");
            aCurrentData.listChallenge.add(newOne);
        }
        ((interfaceMain)getActivity()).changeFragmentChallengeList(1);

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
        return view;
    }

}
