package com.example.temporal;

public class fundingItem {
    String id;
    String title;
    String content;
    String regDate;
    String closeDate;
    Integer targetpoint;
    Integer presentpoint;
    String description;


    public void init(int i) {
        title = "펀딩 테스트" + i;
        content = "펀딩 설명" + i;
    }
}
