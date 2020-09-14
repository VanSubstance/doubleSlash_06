package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class challengeList extends Fragment implements OnItemClickForChallenge {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_list, container,false);

        ArrayList<challengeItem> listChallenge = new ArrayList<challengeItem>();
        for (int i = 0; i < 10; i++) {
            challengeItem newOne = new challengeItem();
            newOne.init(i);
            listChallenge.add(newOne);
        }

        RecyclerView viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        challengeItemAdapter adapter = new challengeItemAdapter(listChallenge, this);
        viewList.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(challengeItem newOne) {
        ((interfaceMain)getActivity()).changeFragmentChallengeItemSpecific(newOne);
    }
}