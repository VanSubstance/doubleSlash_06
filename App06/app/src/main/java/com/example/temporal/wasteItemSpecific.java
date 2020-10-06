package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class wasteItemSpecific extends Fragment {

    wasteItem item;

    public void setItem(wasteItem newOne) {
        item = newOne;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.waste_item, container,false);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textPicture = view.findViewById(R.id.textPicture);
        TextView textDescription = view.findViewById(R.id.textDescription);

        textTitle.setText(item.title);
        textPicture.setText(item.url);
        textDescription.setText(item.desc);


        return view;
    }}