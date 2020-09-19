package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class listMain extends Fragment {
    ArrayList<enrollChecklistItem> sampleCtgr = new ArrayList<enrollChecklistItem>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_main, container, false);

        // 맞는 챌린지 불러오기
        aCurrentData.listChallenge.clear();
        for (int i = 0; i < 10; i++) {
            challengeItem newOne = new challengeItem();
            newOne.init(i, "목록");
            aCurrentData.listChallenge.add(newOne);
        }

        ((interfaceMain)getActivity()).callChallengeList();

        return view;
    }
}

