package com.example.temporal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class wasteItemAdapter extends RecyclerView.Adapter<wasteItemAdapter.ViewHolder> {
    ArrayList<wasteItem> items = new ArrayList<wasteItem>();
    private OnItemClickForWaste mCallback;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDescription;
        ImageView imageWaste;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            imageWaste = itemView.findViewById(R.id.imageWaste);

            itemView.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mCallback.onClick(aCurrentData.listWaste.get(position));
                }
            });
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    wasteItemAdapter(ArrayList<wasteItem> item, OnItemClickForWaste listener) {
        items = item;
        this.mCallback = listener;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public wasteItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.waste_item, parent, false) ;
        wasteItemAdapter.ViewHolder vh = new wasteItemAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(final wasteItemAdapter.ViewHolder holder, int position) {
        holder.textTitle.setText(items.get(position).title);
        holder.textDescription.setText(items.get(position).des);
        Thread callImage = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap imageBitmap = BitmapFactory.decodeStream(new URL("http://ecoseoul.or.kr/wp/wp-content/uploads/2020/09/%EC%A3%BC%EC%84%9D-2020-09-13-101829.png").openStream());
                    holder.imageWaste.setImageBitmap(imageBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        callImage.start();
        try {
            callImage.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return items.size() ;
    }
}
