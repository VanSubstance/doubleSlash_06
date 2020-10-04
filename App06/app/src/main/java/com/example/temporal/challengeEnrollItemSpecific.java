package com.example.temporal;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class challengeEnrollItemSpecific extends Fragment {
    challengeEnrollItem item;

    public void setItem(challengeEnrollItem newOne) {
        item = newOne;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_enroll_item_specific, container,false);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textDescription = view.findViewById(R.id.textDescription);
        textTitle.setText(item.title);
        textDescription.setText(item.desc);
        ((interfaceMain)getActivity()).callCalander();

        return view;
    }

}
