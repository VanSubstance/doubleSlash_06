package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class infoMain extends Fragment{
    RecyclerView viewList;
    userActivityAdapter adapter;
    TextView textNick;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_main, container, false);
        ((interfaceMain) getActivity()).changeFragment(500);

        textNick = view.findViewById(R.id.user);
        textNick.setText(aCurrentData.myInfo.nick);

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(parent.getItemAtPosition(position) == "챌린지")
//                {
//                    for (int i = 0; i < aCurrentData.listUser.size(); i++) {
//                        userItem newOne = new userItem();
//                        newOne.init(i, "마이페이지");
//                        aCurrentData.listUser.add(newOne);
//                    }
//                    ;
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }
}