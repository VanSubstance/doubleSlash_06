package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class challengeMain extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_main, container,false);

        ((interfaceMain)getActivity()).changeFragment(200);
        TextView buttonchallenge = view.findViewById(R.id.challengeMenuReward);
        TextView buttonPoint = view.findViewById(R.id.challengeMenuFunding);
        TextView buttonFriend = view.findViewById(R.id.challengeMenuFriend);

        buttonchallenge.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((interfaceMain)getActivity()).changeFragment(210);
            }
        });

        buttonPoint.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((interfaceMain)getActivity()).changeFragment(220);
            }
        });

        buttonFriend.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((interfaceMain)getActivity()).changeFragment(230);
            }
        });

        return view;
    }}