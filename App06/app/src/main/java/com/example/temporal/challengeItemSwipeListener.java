package com.example.temporal;

import androidx.recyclerview.widget.RecyclerView;

public interface challengeItemSwipeListener {
    boolean onItemMove(int from_position, int to_position);

    void onItemSwipe(int position);

    void onRightClick(int position, RecyclerView.ViewHolder viewHolder);
}