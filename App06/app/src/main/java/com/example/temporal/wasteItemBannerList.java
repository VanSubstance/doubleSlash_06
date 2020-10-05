package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;


public class wasteItemBannerList extends Fragment {
    RecyclerView viewList2;
    wasteItemBannerAdapter adapter2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.waste_list, container,false);
        viewList2 = view.findViewById(R.id.recyclerView2);
        viewList2.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList2);

        adapter2 = new wasteItemBannerAdapter(aCurrentData.listWasteBanner);
        viewList2.setAdapter(adapter2);
        return view;
    }
}