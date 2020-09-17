package com.example.temporal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

public class challengeItemSpecific extends Fragment {
    challengeItem item;

    public void setItem(challengeItem newOne) {
        item = newOne;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_item_specific, container,false);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textDescription = view.findViewById(R.id.textDescription);
        TextView textRegdate = view.findViewById(R.id.textRegdate);
        TextView textClosedate = view.findViewById(R.id.textClosedate);
        TextView textWriter = view.findViewById(R.id.textWriter);
        TextView textLat = view.findViewById(R.id.textLat);
        TextView textLon = view.findViewById(R.id.textLon);

        textTitle.setText(item.title);
        textDescription.setText(item.description);
        textRegdate.setText(item.regDate);
        textClosedate.setText(item.closeDate);
        textWriter.setText(item.writer);
        //textLat.setText(item.lat);
        //textLon.setText(item.lon);

        return view;
    }}