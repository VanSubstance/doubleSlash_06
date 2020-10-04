package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class infoMain extends Fragment implements OnItemClickForInfo{
    RecyclerView viewList;
    userItemAdapter adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_main, container, false);

        ((interfaceMain) getActivity()).changeFragment(500);
//        viewList = view.findViewById(R.id.recyclerView);
//        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        adapter = new userItemAdapter(aCurrentData.listInfo, this);
//        viewList.setAdapter(adapter);
//        PagerSnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(viewList);

//        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new challengeItemSwipeController(adapter));
//        itemTouchhelper.attachToRecyclerView(viewList);

        aCurrentData.listInfo.clear();
        for (int i = 0; i < 6; i++) {
            userItem newOne = new userItem();
            newOne.init(i, "마이페이지");
            aCurrentData.listInfo.add(newOne);
        }


        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return inflater.inflate(R.layout.info_main, container, false);
    }
    @Override
    public void onClick(userItem newOne) {
    }

}