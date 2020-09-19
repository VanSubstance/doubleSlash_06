package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class challengeMain extends Fragment {

    String inputSearch;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.challenge_main, container, false);
        ((interfaceMain) getActivity()).changeFragment(200);

        // 맞는 챌린지 불러오기
        aCurrentData.listChallenge.clear();
        for (int i = 0; i < 10; i++) {
            challengeItem newOne = new challengeItem();
            newOne.init(i, "챌린지");
            aCurrentData.listChallenge.add(newOne);
        }
        ((interfaceMain)getActivity()).callChallengeList();

        final Switch sw = (Switch)view.findViewById(R.id.switch1);
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)view.findViewById(R.id.textView17);
                if(sw.isChecked()) {
                    tv.setText("참여 가능");
                }
                else{
                    tv.setText("챌린지 전체");
                }
            }
        });

        return view;
    }

}
