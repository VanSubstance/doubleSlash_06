package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class homeMain extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_main, container, false);
        FrameLayout frameMain = view.findViewById(R.id.frameHomeWasteList);
        ((interfaceMain)getActivity()).changeFragment(100);
        ConstraintLayout buttonMask = view.findViewById(R.id.buttonMask);
        ConstraintLayout buttonPlastic = view.findViewById(R.id.buttonPlastic);
        ConstraintLayout buttonPaper = view.findViewById(R.id.buttonPaper);
        ConstraintLayout buttonCan = view.findViewById(R.id.buttonCan);
        ConstraintLayout buttonVinyl = view.findViewById(R.id.buttonVinyl);
        ConstraintLayout buttonEtc = view.findViewById(R.id.buttonEtc);

        // 데이터베이스에서 ctgr 맞는거 불러오기
        aCurrentData.listWaste.clear();
        for (int i = 0; i < 10; i++) {
            wasteItem newOne = new wasteItem();
            newOne.init(i);
            newOne.setCtgr("마스크");
            aCurrentData.listWaste.add(newOne);
        }
        ((interfaceMain)getActivity()).changeFragmentWasteList();

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 데이터베이스에서 ctgr 맞는거 불러오기
                for (wasteItem item : aCurrentData.listWaste) {
                    switch (view.getId()) {
                        case R.id.buttonMask:
                            item.setCtgr("마스크");
                            break;
                        case R.id.buttonPlastic:
                            item.setCtgr("플라스틱");
                            break;
                        case R.id.buttonPaper:
                            item.setCtgr("종이");
                            break;
                        case R.id.buttonCan:
                            item.setCtgr("캔/유리");
                            break;
                        case R.id.buttonVinyl:
                            item.setCtgr("비닐");
                            break;
                        case R.id.buttonEtc:
                            item.setCtgr("기타");
                            break;
                    }
                }
                ((interfaceMain)getActivity()).changeFragmentWasteList();
            }
        };

        buttonMask.setOnClickListener(btnListener);
        buttonPlastic.setOnClickListener(btnListener);
        buttonPaper.setOnClickListener(btnListener);
        buttonCan.setOnClickListener(btnListener);
        buttonVinyl.setOnClickListener(btnListener);
        buttonEtc.setOnClickListener(btnListener);

        return view;
    }

}