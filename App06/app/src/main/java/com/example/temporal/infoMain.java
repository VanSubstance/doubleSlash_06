package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        Button buttonList = view.findViewById(R.id.buttonList);
        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.buttonList:
                        ((interfaceMain) getActivity()).changeFragment(510);
                        break;
                    default:
                        break;
                }
            }
        };
        buttonList.setOnClickListener(btnListener);

        aCurrentData.listInfo.clear();
        for (int i = 0; i < 10; i++) {
            userItem newOne = new userItem();
            newOne.init(i, "마이페이지");
            aCurrentData.listInfo.add(newOne);
        }


        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                for (int i = 0; i < aCurrentData.listInfo.size(); i++) {
//                    userItem newOne = new userItem();
//                    newOne.init(i, "마이페이지");
//                    aCurrentData.listInfo.add(newOne);
//                }
                parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }
    @Override
    public void onClick(userItem newOne) {
    }

}