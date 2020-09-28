package com.example.temporal;

import android.app.AlertDialog;
import android.app.Fragment;


public class fundingItem{
    String id;
    String fundingName;
    String fundingTitle;
    String fundingSpecific;
    String specific_title;
    String specific_description;
    String descriptionClick;
    String nowPoint;
    String seekmin;
    String seekmax;
    String point;
    String restPoint;

    public void init(int i) {
        fundingName = "그린피스" + i;
        fundingTitle = "펀딩 제목" + i;
        fundingSpecific = "펀딩 세부내용 문구" + i;
        descriptionClick = "펀딩 설명" + i;
        specific_title = "펀딩 세부내용 제목" + i;
        specific_description = "펀딩 세부내용" + i;
        nowPoint = "나의 현재 포인트" + i;
        seekmin = "0_" + i;
        seekmax = "5000(현재 포인트)_" + i;
        point = "펀딩 포인트" + i;
        restPoint = "잔여 포인트" + i;
    }

}

