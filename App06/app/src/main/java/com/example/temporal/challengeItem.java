package com.example.temporal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class challengeItem {
    String id;
    String title;
    String desc;
    Calendar enr_date;
    Calendar due_date;
    int point = 100;
    Set<Long> days = new TreeSet<>();
    boolean like;
    ArrayList<challengeItemActivity> acvts = new ArrayList<challengeItemActivity>();

    // 테스트를 위한 함수
    public void init(int i, String page) {
        title = page + " 챌린지 테스트" + i;
        desc = "챌린지 설명" + i;
        like = false;
    }

    public void init_enroll(int i) {
        title = "챌린지 등록 테스트 " + i;
        desc = "챌린지 설명 " + i;
        like = false;
    }

    public void setDates(List<Calendar> selectedDates) {
        enr_date = selectedDates.get(0);
        due_date = selectedDates.get(selectedDates.size() - 1);
        for (int i = 0; i < selectedDates.size(); i++) {
            days.add(selectedDates.get(i).getTimeInMillis());
        }
    }

    public void clone(challengeItem original) {
        id = original.id;
        title = original.title;
        desc = original.desc;
        enr_date = original.enr_date;
        due_date = original.due_date;
        days.addAll(original.days);
        like = original.like;
        point = original.point;
        for (int i = 0; i < days.size(); i++) {
            acvts.add(new challengeItemActivity());
        }
    }
}
