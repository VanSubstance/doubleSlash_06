package com.example.temporal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class fundingItemAdapter extends RecyclerView.Adapter<fundingItemAdapter.ViewHolder> {

    private ArrayList<fundingItem> mData = null ;
    private OnItemClickForFunding mCallback;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView funding;
        TextView fundingName;
        TextView fundingTitle;
        TextView fundingSpecific;
        TextView nowPoint;
        TextView seekmin;
        TextView seekmax;
        TextView point;
        TextView restPoint;

//        TextView textDescriptionClick;
//        TextView textSpecificTitle;
//        TextView textSpecificDescription;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            funding = itemView.findViewById(R.id.funding);
            fundingName = itemView.findViewById(R.id.fundingName);
            fundingTitle = itemView.findViewById(R.id.fundingTitle);
            fundingSpecific = itemView.findViewById(R.id.fundingSpecific);
            nowPoint = itemView.findViewById(R.id.textNowPoint);
            seekmin = itemView.findViewById(R.id.seek_min);
            seekmax = itemView.findViewById(R.id.seek_max);
            point = itemView.findViewById(R.id.textPoint);
            restPoint = itemView.findViewById(R.id.textRestPoint);

            Button funding_button = itemView.findViewById(R.id.funding_button);

            funding.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onClick(mData.get(position));
                }
            });
            itemView.setOnScrollChangeListener( new View.OnScrollChangeListener() {

                @Override
                public void onScrollChange(View view, int i, int i1, int oldl, int oldt) {
                    int position = getAdapterPosition();
                    mCallback.onScroll(mData.get(position));
                }

            });

            funding_button.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onFundingClick(mData.get(position));
                }
            });

//            button_left.setOnClickListener( new View.OnClickListener() {
//
//                @Override
//                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    mCallback.onLeftCLick(mData.get(position));
//                }
//            });
//
//            button_right.setOnClickListener( new View.OnClickListener() {
//
//                @Override
//                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    mCallback.onRightCLick(mData.get(position));
//                }
//            });


            }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    fundingItemAdapter(ArrayList<fundingItem> item, OnItemClickForFunding listener) {
        mData = item ;
        this.mCallback = listener;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public fundingItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.funding_item, parent, false) ;
        fundingItemAdapter.ViewHolder vh = new fundingItemAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(fundingItemAdapter.ViewHolder holder, int position) {
        String name = mData.get(position).fundingName;
        String title = mData.get(position).fundingTitle;
        String specific = mData.get(position).fundingSpecific;
        String nowPoint = mData.get(position).nowPoint;
        String seekmin = mData.get(position).seekmin;
        String seekmax = mData.get(position).seekmax;
        String point = mData.get(position).point;
        String restPoint = mData.get(position).restPoint;

        holder.fundingName.setText(name) ;
        holder.fundingTitle.setText(title);
        holder.fundingSpecific.setText(specific);
        holder.nowPoint.setText(nowPoint);
        holder.seekmin.setText(seekmin);
        holder.seekmax.setText(seekmax);
        holder.point.setText(point);
        holder.restPoint.setText(restPoint);


    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

}
