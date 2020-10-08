package com.example.temporal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userItemAdapter extends RecyclerView.Adapter<userItemAdapter.ViewHolder>{

    private ArrayList<userItem> mData = null ;
    private OnItemClickForInfo mCallback;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    userItemAdapter(ArrayList<userItem> item, OnItemClickForInfo listener) {
        mData = item ;
        this.mCallback = listener;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public userItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.info_item, parent, false) ;
//        userItemAdapter.ViewHolder vh = new userItemAdapter.ViewHolder(view) ;

        return new userItemAdapter.ViewHolder(view) ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(userItemAdapter.ViewHolder holder, int position) {
        String title = mData.get(position).title;
        String enr_date = mData.get(position).enr_date;
        String ctr =  mData.get(position).ctr;
        int targ_point = mData.get(position).targ_point;
        int left_point = mData.get(position).left_point;

        holder.title.setText(title) ;
        holder.enr_date.setText(enr_date) ;
        holder.ctr.setText(ctr) ;
        holder.targ_point.setText(String.valueOf(targ_point)) ;
        holder.left_point.setText(String.valueOf(left_point)) ;
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView enr_date;
        TextView targ_point;
        TextView left_point;
        TextView ctr;

        ViewHolder(View itemView) {
            super(itemView) ;

            int position = getAdapterPosition();
            // 뷰 객체에 대한 참조. (hold strong reference)
            title = itemView.findViewById(R.id.inst);
            enr_date = itemView.findViewById(R.id.enr_date);
            targ_point = itemView.findViewById(R.id.targ_point);
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
