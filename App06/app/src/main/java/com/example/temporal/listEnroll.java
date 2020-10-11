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

public class listEnroll extends Fragment implements OnItemClickForChallenge {
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

        final Button buttonDownward = view.findViewById(R.id.buttonDownward);
        final Button buttonUpward = view.findViewById(R.id.buttonUpward);
        buttonUpward.setVisibility(View.GONE);
        buttonDownward.setVisibility(View.VISIBLE);
        buttonDownward.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewList.scrollToPosition(aCurrentData.listMyChallenge.size() - 1);
                buttonDownward.setVisibility(View.GONE);
                buttonUpward.setVisibility(View.VISIBLE);
            }
        });
        buttonUpward.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewList.scrollToPosition(0);
                buttonUpward.setVisibility(View.GONE);
                buttonDownward.setVisibility(View.VISIBLE);
            }
        });
        viewList.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (viewList.canScrollVertically(1)) {
                    buttonUpward.setVisibility(View.GONE);
                    buttonDownward.setVisibility(View.VISIBLE);
                } else if (viewList.canScrollVertically(-1)) {
                    buttonDownward.setVisibility(View.GONE);
                    buttonUpward.setVisibility(View.VISIBLE);
                } else {
                    buttonDownward.setVisibility(View.VISIBLE);
                    buttonUpward.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(challengeItem newOne) {
        ((interfaceMain)getActivity()).changeFragmentChallengeEnrollItemSpecific(newOne);
    }
}
