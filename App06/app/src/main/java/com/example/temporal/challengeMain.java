package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class challengeMain extends Fragment {

    String inputSearch;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_main, container, false);
        ((interfaceMain) getActivity()).changeFragment(200);
        TextView editSearch = view.findViewById(R.id.inputSearch);
        inputSearch = editSearch.getText().toString();

        Button buttonSearch = view.findViewById(R.id.buttonSearch);
        Button buttonEnroll = view.findViewById(R.id.buttonEnroll);

        buttonSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((interfaceMain)getActivity()).changeFragmentChallengeList(2, inputSearch);
            }
        });

        buttonEnroll.setOnClickListener((new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((interfaceMain)getActivity()).changeFragment(210);
            }
        }));

        return view;
    }
}
