package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class wasteMain extends Fragment implements OnItemClickForWaste {
    RecyclerView viewList;
    wasteItemAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.waste_main, container, false);

        TextView textCtgr1 = view.findViewById(R.id.textCtgr1);
        TextView textCtgr2 = view.findViewById(R.id.textCtgr2);
        TextView textCtgr3 = view.findViewById(R.id.textCtgr3);

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.textCtgr1:
                        break;
                    case R.id.textCtgr2:
                        break;
                    case R.id.textCtgr3:
                        break;
                }
            }
        };

        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        viewList.scrollToPosition(aCurrentData.listWaste.size() / 2);

        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        adapter = new wasteItemAdapter(aCurrentData.listWaste, this);
        viewList.setAdapter(adapter);

        return view;
    }

    // 데이터 설정
    public void setData(String ctgr) {
        aCurrentData.listWaste.clear();
        for (int i = 0; i < 10; i++) {
            wasteItem newOne = new wasteItem();
            newOne.init(i);
            newOne.setCtgr(ctgr);
            aCurrentData.listWaste.add(newOne);
        }
    }

    @Override
    public void onClick(wasteItem newOne) {

    }
}
