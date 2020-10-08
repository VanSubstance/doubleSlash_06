package com.example.temporal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class fundingItemAdapter extends RecyclerView.Adapter<fundingItemAdapter.ViewHolder> {

    private ArrayList<fundingItem> mData = null ;
    private OnItemClickForFunding mCallback;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView funding;
        TextView inst;
        TextView inst_desc;
        SeekBar seekBar;

        TextView nowPoint;
        TextView seekmin;
        TextView seekmax;
        TextView targ_point;
        TextView accu_point;
        TextView left_point;

//        TextView textDescriptionClick;
//        TextView textSpecificTitle;
//        TextView textSpecificDescription;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            funding = itemView.findViewById(R.id.funding);
            inst = itemView.findViewById(R.id.inst);
            inst_desc = itemView.findViewById(R.id.inst_desc);
            seekBar = itemView.findViewById(R.id.seekBar);

            nowPoint = itemView.findViewById(R.id.textNowPoint);
            seekmin = itemView.findViewById(R.id.seek_min);
            seekmax = itemView.findViewById(R.id.seek_max);
            targ_point = itemView.findViewById(R.id.textPoint);
            accu_point = itemView.findViewById(R.id.accu_point);
            left_point = itemView.findViewById(R.id.left_point);
            //final int targ = Integer.valueOf(targ_point.getText().toString());
            Button funding_button = itemView.findViewById(R.id.funding_button);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    accu_point.setText(String.valueOf(seekBar.getProgress()));

                    //int targ = Integer.valueOf(targ_point.getText().toString());
//                    int targ;
//                    targ = Integer.parseInt(targ_point.getText().toString());
//                    int targ = Integer.parseInt(String.valueOf(targ_point));
                    int left = 5000 - progress;
                    left_point.setText(String.valueOf(left));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    //point.setText("제대로 돌아감");

                }
            });

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

        String inst = mData.get(position).inst;
        String inst_desc = mData.get(position).inst_desc;

        holder.inst.setText(inst) ;
        holder.inst_desc.setText(inst_desc);

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

}
