package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class listEnroll extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_enroll, container, false);
        // 맞는 챌린지 불러오기
        aCurrentData.listChallengeEnroll.clear();
        for (int i = 0; i < 10; i++) {
            challengeEnrollItem newOne = new challengeEnrollItem();
            newOne.init(i);
            aCurrentData.listChallengeEnroll.add(newOne);
        }
        ((interfaceMain)getActivity()).changeFragmentChallengeEnrollList();

        return view;
    }
}
