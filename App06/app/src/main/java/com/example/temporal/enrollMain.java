package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class enrollMain extends Fragment {
    ArrayList<enrollChecklistItem> sampleCtgr = new ArrayList<enrollChecklistItem>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enroll_main, container, false);
        /**
        Button buttonConfirm = view.findViewById(R.id.buttonConfirm);

        final TextView textCtgr = view.findViewById(R.id.textCtgr);
        final LinearLayout listCtgr = view.findViewById(R.id.listCtgr);
        final FrameLayout frameCheckList = view.findViewById(R.id.frameEnrollMain);

        // 더미 데이터 생성
        enrollChecklistItem sample = new enrollChecklistItem();
        ArrayList<String> sampleSeq = new ArrayList<>();
        sampleSeq.add("젓가락은 재활용으로 버리기");
        sampleSeq.add("호일도 따로 배출하기");
        sampleSeq.add("닭뼈는 일반쓰레기로 버리기");
        sample.init("치킨", sampleSeq);
        sampleCtgr.add(sample);
        sample = new enrollChecklistItem();
        sampleSeq = new ArrayList<>();
        sampleSeq.add("겉 비닐은 일반쓰레기로 버리기");
        sampleSeq.add("페트병은 공기 빼서 뚜껑 닫아서 버리기");
        sample.init("페트병", sampleSeq);
        sampleCtgr.add(sample);

        // 카테고리 리스트 동적 생성
        for (enrollChecklistItem item : sampleCtgr) {
            final enrollChecklistItem temp = item;
            TextView newCtgr = new TextView(view.getContext());
            newCtgr.setText(item.ctgr);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            newCtgr.setLayoutParams(params);
            newCtgr.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textCtgr.setText(temp.ctgr);
                    listCtgr.setVisibility(View.GONE);
                    // 카테고리 별 체크리스트 동적 생성 + 불러오기
                    ((interfaceMain)getActivity()).changeFragmentEnroll(temp);
                    frameCheckList.setVisibility(View.VISIBLE);
                }
            });
            listCtgr.addView(newCtgr);
        }

        textCtgr.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                listCtgr.setVisibility(View.VISIBLE);
            }
        });

        buttonConfirm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textCtgr.getText().toString().equals("카테고리")) {
                    Toast.makeText(view.getContext(), "카테고리를 설정해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    ((interfaceMain)getActivity()).changeFragment(500);
                }
            }
        });
         */

        return view;
    }

}