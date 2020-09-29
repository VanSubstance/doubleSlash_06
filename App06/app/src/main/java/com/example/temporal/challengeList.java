package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class challengeList extends Fragment implements OnItemClickForChallenge {
    RecyclerView viewList;
    challengeItemAdapter adapter;
    // 최초 위치 좌표
    float oldXvalue;
    // Possesion: 0 -> 내꺼 | 1 -> 남의 것
    private int possesion = 0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_list, container,false);

        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new challengeItemAdapter(aCurrentData.listChallenge, this);
        viewList.setAdapter(adapter);

        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        // 내꺼하고 남의 것 삭제 기능 구분
        switch (possesion) {
            case 0:
                // 좌우 넘기기
                ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new challengeItemSwipeController(adapter));
                itemTouchhelper.attachToRecyclerView(viewList);
                break;
            case 1:
                break;
        }
        return view;
    }

    @Override
    public void onClick(challengeItem newOne) {
        switch (possesion) {
            // 0 -> 내꺼 눌렀을 때
            case 0:
                ((interfaceMain)getActivity()).changeFragmentChallengeItemSpecific(newOne);
                break;
            // 1 -> 남의 것 눌렀을 때
            case 1:
                ((interfaceMain)getActivity()).changeFragmentChallengeItemSpecificOther(newOne);
                break;
            default:
                break;
        }
    }


    public void setPossesion(int possesion) {
        this.possesion = possesion;
    }
}