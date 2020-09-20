package com.example.temporal;

import android.app.AlertDialog;
import android.app.Fragment;


public class fundingItem{
    String id;
    String title;
    String specific_title;
    String specific_description;
    String regDate;
    String closeDate;
    Integer targetpoint;
    Integer presentpoint;
    String description;
    String description2;

    public void init(int i) {
        title = "펀딩 제목" + i;
        description = "펀딩 설명" + i;
        specific_title = "펀딩 세부내용 제목" + i;
        specific_description = "펀딩 세부내용" + i;
    }

}

