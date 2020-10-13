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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class challengeItemAdapter extends RecyclerView.Adapter<challengeItemAdapter.ViewHolder> implements challengeItemSwipeListener {

    private ArrayList<challengeItem> mData = new ArrayList<challengeItem>();
    private OnItemClickForChallenge mCallback;
    private retrofitAPI mRetrofitAPI;
    private Retrofit mRetrofit;
    Call<Void> mChallengeItemCall;
    Callback<Void> mChallengeItemCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            System.out.println("사용자 위치 수정 성공");
            System.out.println(response);
            System.out.println(call);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            System.out.println("사용자 위치 수정 실패");
            System.out.println(call);
            t.printStackTrace();
        }
    };
    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://101.101.218.146:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(retrofitAPI.class);
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        return false;
    }

    @Override
    public void onItemSwipe(int position) {

    }

    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {
        setRetrofitInit();
        mChallengeItemCall = mRetrofitAPI.deleteChallengeItem(mData.get(position).chalId);
        mChallengeItemCall.enqueue(mChallengeItemCallback);
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

    public void changeData(ArrayList<challengeItem> newItems) {
        mData.clear();
        mData.addAll(newItems);
        notifyDataSetChanged();
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
        holder.textTitle.setText(title) ;
        holder.textDescription.setText(description);
        holder.progressBar.setProgress((int) ((double) mData.get(position).progress / mData.get(position).days.size() * 100));
        holder.textProgress.setText("등록일수 " + mData.get(position).progress +  "/" + mData.get(position).days.size());
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
        TextView textProgress;
        ProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            progressBar = itemView.findViewById(R.id.progressBar);
            textProgress = itemView.findViewById(R.id.textProgress);
            itemView.setOnClickListener( new View.OnClickListener() {
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onClick(mData.get(position));
                }
            });
        }
    }
}
