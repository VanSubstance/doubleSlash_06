package com.example.temporal;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import java.util.Calendar;

public class challengeEnrollItemSpecific extends Fragment {
    challengeItem item;

    public void setItem(challengeItem newOne) {
        item = newOne;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_enroll_item_specific, container,false);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textDescription = view.findViewById(R.id.textDescription);
        textTitle.setText(item.title);
        textDescription.setText(item.desc);
        final CalendarView calendarView = view.findViewById(R.id.calendarView);
        calendarView.setSelectionType(SelectionType.RANGE);
        calendarView.setWeekendDayTextColor(Color.RED);
        Button buttonEnroll = view.findViewById(R.id.buttonEnroll);
        buttonEnroll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setDates(calendarView.getSelectedDates());
                item.like = false;
                aCurrentData.listMyChallenge.add(item);
                ((interfaceMain)getActivity()).callMenu(R.id.menuHome);
            }
        });

        return view;
    }

}
