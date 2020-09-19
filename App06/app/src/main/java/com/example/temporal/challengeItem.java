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
    public void init(int i, String page) {
        title = page + " 챌린지 테스트" + i;
        description = "챌린지 설명" + i;
        regDate = "시작일" + i;
        closeDate = "마감일" + i;
        writer = "작성자" + i;
        lat = 0.0 + i;
        lon = 0.0 + i;




    }
}
