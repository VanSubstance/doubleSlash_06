package com.example.temporal;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class challengeItemAdapter extends RecyclerView.Adapter<challengeItemAdapter.ViewHolder> implements challengeItemSwipeListener {

    private ArrayList<challengeItem> mData = new ArrayList<challengeItem>();
    private OnItemClickForChallenge mCallback;

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        return false;
    }

    @Override
    public void onItemSwipe(int position) {

    }

    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {
        // 서버에서도 삭제
        aCurrentData.listMyChallenge.remove(mData.get(position));
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    challengeItemAdapter(ArrayList<challengeItem> item, OnItemClickForChallenge listener) {
        mData.clear();
        mData.addAll(item);
        this.mCallback = listener;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public challengeItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.challenge_item, parent, false) ;
        challengeItemAdapter.ViewHolder vh = new challengeItemAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(challengeItemAdapter.ViewHolder holder, int position) {
        String title = mData.get(position).title;
        String description = mData.get(position).des;
        if (mData.get(position).favorite) { // 즐겨찾기일 경우
            holder.imageLike.setColorFilter(Color.GREEN);
        } else { // 아닐 경우
            holder.imageLike.setColorFilter(Color.BLACK);
        }
        holder.textTitle.setText(title) ;
        holder.textDescription.setText(description);
        holder.progressBar.setProgress(mData.get(position).progress);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDescription;
        ImageView imageLike;
        ProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            imageLike = itemView.findViewById(R.id.imageLike);
            progressBar = itemView.findViewById(R.id.progressBar);
            itemView.setOnClickListener( new View.OnClickListener() {
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onClick(mData.get(position));
                }
            });
            imageLike.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onClickLike(mData.get(position), imageLike);
                }
            });
        }
    }
}
