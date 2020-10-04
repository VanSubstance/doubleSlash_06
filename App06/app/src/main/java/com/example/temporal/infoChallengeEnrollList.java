package com.example.temporal;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class infoChallengeEnrollList extends Fragment implements OnItemClickForChallengeEnroll {
    RecyclerView viewList;
    challengeEnrollItemAdapter adapter;
    ArrayList<challengeEnrollItem> items = new ArrayList<challengeEnrollItem>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_enroll, container, false);
        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        for (int i = 0; i < aCurrentData.listChallengeEnroll.size(); i++) {
            if (aCurrentData.listChallengeEnroll.get(i).like) {
                items.add(aCurrentData.listChallengeEnroll.get(i));
            }
        }
        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        adapter = new challengeEnrollItemAdapter(items, this);
        viewList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(challengeEnrollItem newOne) {
        ((interfaceMain)getActivity()).changeFragmentChallengeEnrollItemSpecific(newOne);
    }

    @Override
    public void onClickLike(challengeEnrollItem newOne, ImageView imageLike) {
    }
}
