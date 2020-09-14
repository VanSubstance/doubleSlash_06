package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class wasteList extends Fragment implements OnItemClickForWaste {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.waste_list, container,false);
        
        ArrayList<wasteItem> listwaste = new ArrayList<wasteItem>();
        for (int i = 0; i < 10; i++) {
            wasteItem newOne = new wasteItem();
            newOne.init(i);
            listwaste.add(newOne);
        }

        RecyclerView viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        wasteItemAdapter adapter = new wasteItemAdapter(listwaste, this);
        viewList.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(wasteItem newOne) {
        ((interfaceMain)getActivity()).changeFragmentWasteItemSpecific(newOne);
    }
}