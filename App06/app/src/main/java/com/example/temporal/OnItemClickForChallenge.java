package com.example.temporal;

import android.widget.ImageView;

public interface OnItemClickForChallenge {
    void onClick(challengeItem newOne);
    void onClickLike(challengeItem newOne, ImageView imageLike);
}
