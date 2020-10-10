package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// changeFragmentChallengeComplete(challenge newOne); 으로 불러옴
public class challengeComplete extends Fragment {
    challengeItem item = new challengeItem();

    public void setItem(challengeItem newOne) {
        item.clone(newOne);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_complete, container, false);
        TextView textCongrat = view.findViewById(R.id.textCongrat);
        textCongrat.setText("축하드려요\\n" + aCurrentData.myInfo.nick + " 님\\n챌린지를 성공했어요!");

        TextView textTitle = view.findViewById(R.id.textTitle);
        textTitle.setText(item.title);

        TextView textDesc = view.findViewById(R.id.textDescription);
        textDesc.setText(item.des);

        TextView dateStart = view.findViewById(R.id.textStartDate);

        TextView dateEnd = view.findViewById(R.id.textEndDate);

        TextView textReward = view.findViewById(R.id.textReward);

        TextView textPointTotal = view.findViewById(R.id.textPointTotal);

        TextView textEncourage = view.findViewById(R.id.textEncourage);

        return view;
    }
}
