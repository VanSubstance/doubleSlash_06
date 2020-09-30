package com.example.temporal;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class fundingList extends Fragment implements OnItemClickForFunding {
    RecyclerView viewList;
    fundingItemAdapter adapter;

//    ConstraintLayout funding_list = viewList.findViewById(R.id.funding_list);
//    TextView funding = viewList.findViewById(R.id.funding);
//    TextView fundingTitle = viewList.findViewById(R.id.fundingTitle);
//    TextView fundingSpecific = viewList.findViewById(R.id.fundingSpecific);
//    ConstraintLayout funding_click = viewList.findViewById(R.id.funding_click);

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.funding_list, container,false);

        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        adapter = new fundingItemAdapter(aCurrentData.listFunding, this);
        viewList.setAdapter(adapter);

        return view;

    }

    @Override
        public void onClick(fundingItem newOne) {
        ConstraintLayout funding_list = viewList.findViewById(R.id.funding_list);

        TextView funding = viewList.findViewById(R.id.funding);
        TextView desc = viewList.findViewById(R.id.desc);
        TextView spec_desc = viewList.findViewById(R.id.spec_desc);
        SeekBar seekBar = viewList.findViewById(R.id.seekBar);
        TextView start_point = viewList.findViewById(R.id.start_point);
        TextView targ_point = viewList.findViewById(R.id.targ_point);

        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.funding_translate_top);   // 에니메이션 설정 파일
        funding_list.startAnimation(anim);

        desc.setVisibility(View.GONE);
        spec_desc.setVisibility(View.GONE);
        funding.setVisibility(View.GONE);
        seekBar.setVisibility(View.GONE);
        start_point.setVisibility(View.GONE);
        targ_point.setVisibility(View.GONE);

        funding_list.setVisibility(View.VISIBLE);

        funding_list.setBackgroundColor(Color.parseColor("#79C3A0"));
    }

    @Override
    public void onScroll(fundingItem newOne) {

        TextView textNowPoint = viewList.findViewById(R.id.textNowPoint);
        TextView textPoint = viewList.findViewById(R.id.textPoint);
        TextView textRestPoint = viewList.findViewById(R.id.textRestPoint);
//        TextView textSpecificDescription = viewList.findViewById(R.id.textSpecificDescription);
//        Button fundingbutton = viewList.findViewById(R.id.funding_button);
//        TextView textTitleClick = viewList.findViewById(R.id.textTitleClick);
//        TextView textDescriptionClick = viewList.findViewById(R.id.textDescriptionClick);

        textNowPoint.setVisibility(View.VISIBLE);
        textPoint.setVisibility(View.VISIBLE);
        textRestPoint.setVisibility(View.VISIBLE);
//            textSpecificDescription.setVisibility(View.VISIBLE);
//            fundingbutton.setVisibility(View.VISIBLE);
//            textTitle.setVisibility(View.GONE);
//            textDescription.setVisibility(View.GONE);

        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.funding_translate_top);   // 에니메이션 설정 파일
        viewList.startAnimation(anim);
    }

    @Override
    public void onFundingClick(fundingItem newOne) {
        ConstraintLayout funding_list = viewList.findViewById(R.id.funding_list);
        ConstraintLayout funding_click = viewList.findViewById(R.id.funding_click);

        funding_list.setVisibility(View.GONE);
        funding_click.setVisibility(View.VISIBLE);
        funding_click.setBackgroundColor(Color.parseColor("#79C3A0"));

    }

}

