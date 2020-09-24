package com.example.temporal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fundingList extends Fragment implements OnItemClickForFunding {
    RecyclerView viewList;
    fundingItemAdapter adapter;
    //private OnItemClickForFunding dofunding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.funding_list, container,false);

//        Button buttonFunding = view.findViewById(R.id.funding_button);
//        buttonFunding.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                buttonFunding.Button.setVisibility(View.GONE);
//            }
//        });

        //ConstraintLayout fundinglayout = view.findViewById(R.id.funding_list);

//        Button buttonFunding = view.findViewById(R.id.funding_button);
//
//        buttonFunding.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("펀딩이 완료되었습니다!");
//                builder.setMessage("펀딩 완료 메시지");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        //토스트 메시지
//                        Toast.makeText(getActivity().getApplicationContext(), "펀딩 완료", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                //builder.setNeutralButton("취소", null);
//                builder.show();
//            }
//        });

        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        adapter = new fundingItemAdapter(aCurrentData.listFunding, this);
        viewList.setAdapter(adapter);

        TextView textSpecificTitle = view.findViewById(R.id.textSpecificTitle);
        TextView textSpecificDescription = view.findViewById(R.id.textSpecificDescription);

//        textSpecificTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.funding_translate_top);   // 에니메이션 설정 파일
//                viewList.startAnimation(anim);
//            }
//        });

        return view;

    }

    @Override
        public void onClick(fundingItem newOne) {
        //((interfaceMain)getActivity()).changeFragmentFundingItemSpecific(newOne);
//        View view = inflater.inflate(R.layout.funding_list, container,false);
//        TextView textSpecificTitle = view.findViewById(R.id.textSpecificTitle);
//        TextView textSpecificDescription = view.findViewById(R.id.textSpecificDescription);
//
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.funding_translate_top);   // 에니메이션 설정 파일
        viewList.startAnimation(anim);

//
////        textSpecificTitle.(View.VISIBLE);
//
//
////          Intent intent = new Intent(getActivity(), fundingList.class);
////          intent.putExtra("extra_data", "from subactivity");
////          getActivity().setResult(Activity.RESULT_OK, intent);
////          getActivity().overridePendingTransition(R.anim.funding_translate_top, R.anim.stay);
////          getActivity().finish();
//
////        Intent intent = new Intent(getActivity(), fundingList.class);
////        Intent intent = new Intent();
////        getActivity().startActivity(intent);
////        (getActivity()).overridePendingTransition(R.anim.funding_translate_top, R.anim.stay);
////
////        ((MainActivity)getActivity()).overridePendingTransition(0, 0);
////        getActivity().finish();
////        ((MainActivity)getActivity()).overridePendingTransition(R.anim.stay,R.anim.funding_translate_bottom);
//
    }

}

