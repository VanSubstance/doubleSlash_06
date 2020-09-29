package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class challengeList extends Fragment implements OnItemClickForChallenge {
    RecyclerView viewList;
    challengeItemAdapter adapter;
    // Possesion: 0 -> 내꺼 | 1 -> 남의 것
    private int possesion = 0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_list, container,false);

        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        adapter = new challengeItemAdapter(aCurrentData.listChallenge, this);
        viewList.setAdapter(adapter);
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