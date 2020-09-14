package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class homeMain extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_main, container, false);
        EditText editSearch = view.findViewById(R.id.inputSearch);
        String inputSearch = editSearch.getText().toString();
        Button buttonSearch = view.findViewById(R.id.buttonSearch);
        FrameLayout frameMain = view.findViewById(R.id.frameHomeWasteList);
        ((interfaceMain)getActivity()).changeFragment(100);

        return view;
    }

}