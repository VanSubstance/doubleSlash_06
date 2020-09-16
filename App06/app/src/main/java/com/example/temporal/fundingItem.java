package com.example.temporal;

public class fundingItem {
    String id;
    String title;
    String description;
    
    public void init(int i) {
        title = "펀딩 테스트" + i;
        description = "펀딩 설명" + i;
    }
}
