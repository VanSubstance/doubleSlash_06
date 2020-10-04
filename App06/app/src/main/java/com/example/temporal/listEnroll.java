package com.example.temporal;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class listEnroll extends Fragment implements OnItemClickForChallengeEnroll {
    RecyclerView viewList;
    challengeEnrollItemAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_enroll, container, false);
        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        adapter = new challengeEnrollItemAdapter(aCurrentData.listChallengeEnroll, this);
        viewList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(challengeEnrollItem newOne) {
        ((interfaceMain)getActivity()).changeFragmentChallengeEnrollItemSpecific(newOne);
    }

    @Override
    public void onClickLike(challengeEnrollItem newOne, ImageView imageLike) {
        if (newOne.like) { // 이미 즐겨찾기일 경우
            imageLike.setColorFilter(Color.BLACK);
            newOne.like = false;
        } else { // 즐겨찾기 아닐 경우
            imageLike.setColorFilter(Color.GREEN);
            newOne.like = true;
        }
    }
}
