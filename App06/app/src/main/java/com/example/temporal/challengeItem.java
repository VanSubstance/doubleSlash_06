package com.example.temporal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class challengeItem {
    String chalId;
    int mem_id;
    String title;
    String des;
    String regdate;
    String deadline;
    int chalPoint = 100;
    Set<Long> days = new TreeSet<>();
    boolean favorite;
    int progress = 0;
    ArrayList<challengeItemActivity> acvts = new ArrayList<challengeItemActivity>();

    public void setFromFrame(challengeFrameItem item) {
        chalId = item.ch_id;
        title = item.title;
        des = item.des;
        chalPoint = item.point;
        favorite = false;
    }

    public void setDates(List<Calendar> selectedDates) {
        regdate = selectedDates.get(0).toString();
        deadline = selectedDates.get(selectedDates.size() - 1).toString();
        for (int i = 0; i < selectedDates.size(); i++) {
            days.add(selectedDates.get(i).getTimeInMillis());
        }
    }

    public void clone(challengeItem original) {
        chalId = original.chalId;
        title = original.title;
        des = original.des;
        regdate = original.regdate;
        deadline = original.deadline;
        days.addAll(original.days);
        favorite = original.favorite;
        chalPoint= original.chalPoint;
        for (int i = 0; i < days.size(); i++) {
            acvts.add(new challengeItemActivity());
        }
        if (days.size() != 0) {
            progress = acvts.size() / days.size() * 100;
        }
    }
}
