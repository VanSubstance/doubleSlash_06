package com.example.temporal;

public class challengeEnrollItem {
    String id;
    String title;
    String desc;
    String enr_date;
    String due_date;
    Double lat;
    Double lon;
    String nick;
    // 내용은 string? 아님 Array<String>?
    String content;

    // 테스트를 위한 함수
    public void init(int i) {
        title = "챌린지 등록 테스트 " + i;
        desc = "챌린지 설명 " + i;
        enr_date = "시작일 " + i;
        due_date = "마감일 " + i;
        nick = "작성자 " + i;
        lat = 0.0 + i;
        lon = 0.0 + i;

    }
}
