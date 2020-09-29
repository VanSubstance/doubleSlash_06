package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class listMain extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_main, container, false);

        // 맞는 챌린지 불러오기
        aCurrentData.listChallenge.clear();
        for (int i = 0; i < 10; i++) {
            challengeItem newOne = new challengeItem();
            newOne.init(i, "목록");
            aCurrentData.listChallenge.add(newOne);
        }
        ((interfaceMain)getActivity()).changeFragmentChallengeList(0);

        return view;
    }
}

