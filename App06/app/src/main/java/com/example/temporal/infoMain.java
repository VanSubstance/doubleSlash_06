package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class infoMain extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_main, container, false);

        aCurrentData.listInfo.clear();
        for (int i = 0; i < 10; i++) {
            userItem newOne = new userItem();
            newOne.init(i, "마이페이지");
            aCurrentData.listInfo.add(newOne);
        }
        ((interfaceMain)getActivity()).changeFragmentInfoList();

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return inflater.inflate(R.layout.info_main, container, false);
    }


}