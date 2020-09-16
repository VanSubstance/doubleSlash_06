package com.example.temporal;

public class challengeItem {
    String id;
    String title;
    String description;
    String regDate;
    String closeDate;
    String writer;
    Double lat;
    Double lon;
    // 내용은 string? 아님 Array<String>?
    String content;

    // 테스트를 위한 함수
    public void init(int i) {
        title = "챌린지 테스트" + i;
        description = "챌린지 설명" + i;
    }
}
