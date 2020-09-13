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

public class enrollMain extends Fragment {

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enroll_main, container, false);
        Button buttonConfirm = view.findViewById(R.id.buttonConfirm);

        final TextView textCtgr = view.findViewById(R.id.textCtgr);
        final LinearLayout listCtgr = view.findViewById(R.id.listCtgr);
        final TextView c1 = view.findViewById(R.id.c1);
        final TextView c2 = view.findViewById(R.id.c2);
        final TextView c3 = view.findViewById(R.id.c3);
        final FrameLayout frameCheckList = view.findViewById(R.id.frameEnrollMain);

        textCtgr.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                listCtgr.setVisibility(View.VISIBLE);
            }
        });

        c1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textCtgr.setText(c1.getText().toString());
                listCtgr.setVisibility(View.GONE);
                ((interfaceMain)getActivity()).changeFragmentEnroll();
                frameCheckList.setVisibility(View.VISIBLE);
            }
        });

        c2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textCtgr.setText(c2.getText().toString());
                listCtgr.setVisibility(View.GONE);
                ((interfaceMain)getActivity()).changeFragmentEnroll();
                frameCheckList.setVisibility(View.VISIBLE);
            }
        });

        c3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                textCtgr.setText(c3.getText().toString());
                listCtgr.setVisibility(View.GONE);
                ((interfaceMain)getActivity()).changeFragmentEnroll();
                frameCheckList.setVisibility(View.VISIBLE);
            }
        });

        buttonConfirm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textCtgr.getText().toString().equals("카테고리")) {
                    Toast.makeText(container.getContext(), "카테고리를 설정해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    ((interfaceMain)getActivity()).changeFragment(410);
                }
            }
        });

        return view;
    }

}