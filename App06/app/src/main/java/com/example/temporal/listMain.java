package com.example.temporal;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class listMain extends Fragment implements OnItemClickForChallenge {
    RecyclerView viewList;
    challengeItemAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_main, container, false);
        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new challengeItemAdapter(aCurrentData.listMyChallenge, this);
        viewList.setAdapter(adapter);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new challengeItemSwipeController(adapter));
        itemTouchhelper.attachToRecyclerView(viewList);

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
        ((interfaceMain)getActivity()).changeFragmentChallengeItemSpecific(newOne);
    }

    @Override
    public void onClickLike(challengeItem newOne, ImageView imageLike) {
        if (newOne.like) { // 이미 즐겨찾기일 경우
            imageLike.setColorFilter(Color.BLACK);
            newOne.like = false;
        } else { // 즐겨찾기 아닐 경우
            imageLike.setColorFilter(Color.GREEN);
            newOne.like = true;
        }
    }
}

