package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.recyclerview.widget.OrientationHelper;

public class calander extends Fragment {

    private CalendarView calendarView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calander, container,false);
        calendarView = (CalendarView) view.findViewById(R.id.calendar_view);
        return view;
    }

}
