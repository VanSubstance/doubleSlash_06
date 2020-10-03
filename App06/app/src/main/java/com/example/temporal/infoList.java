package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class infoList extends Fragment implements OnItemClickForInfo{
    RecyclerView viewList;
    userItemAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_list, container,false);

        viewList = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        viewList.setLayoutManager(linearLayoutManager);
        adapter = new userItemAdapter(aCurrentData.listInfo, this);
        viewList.setAdapter(adapter);

//        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        return view;
    }

    @Override
    public void onClick(userItem newOne) {
    }

    @Override
    public void onScroll(userItem newOne) {

    }

}
