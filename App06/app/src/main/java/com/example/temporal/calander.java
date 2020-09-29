package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.OrientationHelper;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

public class calander extends Fragment {

    private CalendarView calendarView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calander, container,false);
        calendarView = (CalendarView) view.findViewById(R.id.calendar_view);
        calendarView.setCalendarOrientation(OrientationHelper.HORIZONTAL);
        return view;
    }

}
