package com.example.temporal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userActivityAdapter extends RecyclerView.Adapter<userActivityAdapter.ViewHolder>{

    private ArrayList<userActivity> mData = null ;
    private OnItemClickForInfo mCallback;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    userActivityAdapter(ArrayList<userActivity> item, OnItemClickForInfo listener) {
        mData = item ;
        this.mCallback = listener;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public userActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.info_item, parent, false) ;
//        userActivityAdapter.ViewHolder vh = new userActivityAdapter.ViewHolder(view) ;

        return new userActivityAdapter.ViewHolder(view) ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(userActivityAdapter.ViewHolder holder, int position) {
        userActivity item = mData.get(position);
        switch (item.type) {
            case 0:
                holder.ctr.setText("챌린지 완료");
                holder.targ_point.setText("+ " + item.point);
                break;
            case 1:
                holder.ctr.setText("펀딩 활동");
                holder.targ_point.setText("- " + item.point);
                break;
            case 2:
                holder.ctr.setText("챌린지 활동");
                holder.targ_point.setText("+ " + item.point);
                break;
            case 3:
                holder.ctr.setText("완료된 펀딩");
                holder.targ_point.setText("- " + item.point);
                break;
        }
        if (item.type == 0) {
        } else {
        }
        holder.title.setText(item.title);
        holder.date.setText(item.date);

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        TextView targ_point;
        TextView left_point;
        TextView ctr;

        ViewHolder(View itemView) {
            super(itemView) ;
            // 뷰 객체에 대한 참조. (hold strong reference)
            title = itemView.findViewById(R.id.textTitle);
            date = itemView.findViewById(R.id.enr_date);
            targ_point = itemView.findViewById(R.id.point);
            left_point = itemView.findViewById(R.id.left_point);
            ctr = itemView.findViewById(R.id.ctr);

            itemView.setOnClickListener( new View.OnClickListener() {
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onClick(mData.get(position));
                }
            });

        }
    }
}
