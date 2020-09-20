package com.example.temporal;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fundingMain extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.funding_main, container, false);
        ((interfaceMain) getActivity()).changeFragment(300);

        aCurrentData.listFunding.clear();
        for (int i = 0; i < 10; i++) {
            fundingItem newOne = new fundingItem();
            newOne.init(i);
            aCurrentData.listFunding.add(newOne);
        }
        ((interfaceMain)getActivity()).changeFragmentFundingList();

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
        //return inflater.inflate(R.layout.funding_main, container, false);
    }
}
