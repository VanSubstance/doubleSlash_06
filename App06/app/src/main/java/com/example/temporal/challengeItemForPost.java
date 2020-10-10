package com.example.temporal;

import java.util.Calendar;
import java.util.List;

public class challengeItemForPost {
    int memId;
    String chalfrId;
    String regdate;
    String deadline;
    boolean favorite;


    public void setDates(List<Calendar> selectedDates) {
        regdate = selectedDates.get(0).toString();
        deadline = selectedDates.get(selectedDates.size() - 1).toString();
    }
}
