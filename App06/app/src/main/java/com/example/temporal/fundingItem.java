package com.example.temporal;

import android.app.AlertDialog;
import android.app.Fragment;


public class fundingItem{
    int id;
    String title;
    String titleClick;
    String specific_title;
    String specific_description;
    String regDate;
    String closeDate;
    Integer targetpoint;
    Integer presentpoint;
    String description;
    String descriptionClick;
    String description2;

    public void init(int i) {
        title = "펀딩 제목" + i;
        description = "펀딩 설명" + i;
        titleClick = "펀딩 제목" + i;
        descriptionClick = "펀딩 설명" + i;
        specific_title = "펀딩 세부내용 제목" + i;
        specific_description = "펀딩 세부내용" + i;
    }

}

