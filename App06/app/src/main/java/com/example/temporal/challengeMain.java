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

        //TextView switchView = (TextView) view.findViewById(R.id.switchon_text);
        //switchView.setVisibility(View.VISIBLE);

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
//    class visibilitySwitchListener implements CompoundButton.OnCheckedChangeListener{
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            if(isChecked)
//                switchView.setVisibility(View.INVISIBLE);
//            else
//                textView.setVisibility(View.VISIBLE);
//        }
//    }

}
