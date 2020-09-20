package com.example.temporal;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fundingItemSpecific extends Fragment {
    fundingItem item;

    public void setItem(fundingItem newOne) {
        item = newOne;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.funding_item_specific, container,false);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textDescription = view.findViewById(R.id.textDescription);
        textTitle.setText(item.title);
        textDescription.setText(item.description);

        Button buttonFunding = view.findViewById(R.id.funding_button);

        buttonFunding.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("펀딩이 완료되었습니다!");
                builder.setMessage("펀딩 완료 메시지");
                builder.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(getActivity(),"펀딩 완료",Toast.LENGTH_SHORT).show();
                    }
                });

                //builder.setNeutralButton("취소", null);
                builder.show();
            }
        });

        return view;
    }}