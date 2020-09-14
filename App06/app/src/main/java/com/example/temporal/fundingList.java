package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fundingList extends Fragment implements OnItemClickForFunding {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.funding_list, container,false);

        ArrayList<fundingItem> listfunding = new ArrayList<fundingItem>();
        for (int i = 0; i < 10; i++) {
            fundingItem newOne = new fundingItem();
            newOne.init(i);
            listfunding.add(newOne);
        }

        RecyclerView viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        fundingItemAdapter adapter = new fundingItemAdapter(listfunding, this);
        viewList.setAdapter(adapter);
        
        return view;
    }

    @Override
    public void onClick(fundingItem newOne) {
        ((interfaceMain)getActivity()).changeFragmentFundingItemSpecific(newOne);
    }
}
