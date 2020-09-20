package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fundingList extends Fragment implements OnItemClickForFunding {
    RecyclerView viewList;
    fundingItemAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.funding_list, container,false);

        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        adapter = new fundingItemAdapter(aCurrentData.listFunding, this);
        viewList.setAdapter(adapter);
        return view;

    }

    @Override
    public void onClick(fundingItem newOne) {
        ((interfaceMain)getActivity()).changeFragmentFundingItemSpecific(newOne);
    }
}
