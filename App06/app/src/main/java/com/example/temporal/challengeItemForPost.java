package com.example.temporal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class challengeItemForPost {
    int memId;
    int chalfrId;
    String regdate;
    String deadline;
    int chalPoint;


    public void setDates(List<Calendar> selectedDates) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        regdate = sdf.format(selectedDates.get(0).getTime());
        deadline = sdf.format(selectedDates.get(selectedDates.size() - 1).getTime());
    }
}
