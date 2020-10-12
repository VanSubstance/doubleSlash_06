package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class homeMain extends Fragment implements OnItemClickForChallenge {
    RecyclerView viewList2;
    wasteItemBannerAdapter adapter2;
    RecyclerView viewList;
    challengeItemAdapter adapter;
    ArrayList<challengeItem> items = new ArrayList<challengeItem>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_main, container, false);
        for (challengeItem it : aCurrentData.listMyChallenge) {
            if (items.size() < 3) {
                items.add(it);
            } else {
                for (challengeItem currentOne : items) {
                    if (currentOne.progress <= it.progress) {
                        items.remove(currentOne);
                        items.add(it);
                        break;
                    }
                }
            }
        }
        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);
        // 좌우 넘기기
        adapter = new challengeItemAdapter(items, this);
        viewList.setAdapter(adapter);

        viewList2 = view.findViewById(R.id.recyclerView2);
        viewList2.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        // 부드럽게 넘기기
        snapHelper.attachToRecyclerView(viewList2);
        adapter2 = new wasteItemBannerAdapter(aCurrentData.listWasteBanner);
        viewList2.setAdapter(adapter2);

        ConstraintLayout buttonMask = view.findViewById(R.id.buttonMask);
        ConstraintLayout buttonPlastic = view.findViewById(R.id.buttonPlastic);
        ConstraintLayout buttonPaper = view.findViewById(R.id.buttonPaper);
        ConstraintLayout buttonCan = view.findViewById(R.id.buttonCan);
        ConstraintLayout buttonVinyl = view.findViewById(R.id.buttonVinyl);
        ConstraintLayout buttonEtc = view.findViewById(R.id.buttonEtc);
        Button buttonList = view.findViewById(R.id.buttonList);

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 데이터베이스에서 ctgr 맞는거 불러오기
                switch (view.getId()) {
                    case R.id.buttonMask:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("마스크");
                        break;
                    case R.id.buttonPlastic:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("plastic");
                        break;
                    case R.id.buttonPaper:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("paper");
                        break;
                    case R.id.buttonCan:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("glass");
                        break;
                    case R.id.buttonVinyl:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("vinyl");
                        break;
                    case R.id.buttonEtc:
                        ((interfaceMain) getActivity()).changeFragmentWasteCtgr("etc");
                        break;
                    case R.id.buttonList:
                        ((interfaceMain) getActivity()).changeFragment(110);
                        break;
                }
            }
        };

        buttonMask.setOnClickListener(btnListener);
        buttonPlastic.setOnClickListener(btnListener);
        buttonPaper.setOnClickListener(btnListener);
        buttonCan.setOnClickListener(btnListener);
        buttonVinyl.setOnClickListener(btnListener);
        buttonEtc.setOnClickListener(btnListener);
        buttonList.setOnClickListener(btnListener);
        return view;
    }

    @Override
    public void onClick(challengeItem newOne) {
        ((interfaceMain)getActivity()).changeFragmentChallengeItemSpecific(newOne);
    }
}