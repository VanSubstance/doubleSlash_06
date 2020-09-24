package com.example.temporal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
        TextView textTitle;
        TextView textDescription;
        TextView textSpecificTitle;
        TextView textSpecificDescription;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            textSpecificTitle = itemView.findViewById(R.id.textSpecificTitle);
            textSpecificDescription = itemView.findViewById(R.id.textSpecificDescription);
            final RecyclerView viewList = itemView.findViewById(R.id.recyclerView);

            itemView.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onClick(mData.get(position));
                }
            });

//            textSpecificTitle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Context context = null;
//                    Animation anim = AnimationUtils.loadAnimation(context, R.anim.funding_translate_top);   // 에니메이션 설정 파일
//                    viewList.startAnimation(anim);
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
        String title = mData.get(position).title;
        String description = mData.get(position).description;
        String specific_title = mData.get(position).specific_title;
        String specific_description = mData.get(position).specific_description;

        holder.textTitle.setText(title) ;
        holder.textDescription.setText(description);
        holder.textSpecificTitle.setText(specific_title);
        holder.textSpecificDescription.setText(specific_description);
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.funding_item, container, false);

        Button buttonFunding = view.findViewById(R.id.funding_button);

        buttonFunding.setOnClickListener(new Button.OnClickListener() {
            private Context context;
            private Activity activity;

            @Override
            public void onClick(View view) {

                final Context context = null;
                final Activity activity = null;
                this.context = context;
                this.activity = activity;
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("펀딩이 완료되었습니다!");
                builder.setMessage("펀딩 완료 메시지");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 메시지
                        Toast.makeText(activity.getApplicationContext(), "펀딩 완료", Toast.LENGTH_SHORT).show();
                    }
                });

                //builder.setNeutralButton("취소", null);
                builder.show();
            }
        });
    }

}
