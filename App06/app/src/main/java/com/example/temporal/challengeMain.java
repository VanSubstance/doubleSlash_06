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
        ((interfaceMain)getActivity()).changeFragmentChallengeList(1);
        return view;
    }

}
