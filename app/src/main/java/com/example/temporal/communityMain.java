package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class communityMain extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.community_main, container,false);
        Button buttonWrite = (Button) view.findViewById(R.id.buttonWrite);
        buttonWrite.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                ((interfaceMain)getActivity()).changeFragment(1);
            }
        });

        return view;
    }}
