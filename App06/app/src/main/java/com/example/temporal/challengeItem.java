package com.example.temporal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class challengeItem {
    int chalId;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        regdate = sdf.format(selectedDates.get(0).getTime());
        deadline = sdf.format(selectedDates.get(selectedDates.size() - 1).getTime());
        for (int i = 0; i < selectedDates.size(); i++) {
            days.add(selectedDates.get(i).getTimeInMillis());
        }
    }

    public void setDatesFromServer() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar start = Calendar.getInstance();
        start.setTime(sdf.parse(regdate));
        Calendar temp = Calendar.getInstance();
        temp.setTime(sdf.parse(regdate));
        Calendar end = Calendar.getInstance();
        end.setTime(sdf.parse(deadline));
        for (int i = 0; i <= end.DATE - start.DATE; i++ ) {
            days.add(temp.getTimeInMillis());
            temp.add(temp.DATE, 1);
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
