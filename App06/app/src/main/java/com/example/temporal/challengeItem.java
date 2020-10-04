package com.example.temporal;

public class challengeItem {
    String id;
    String title;
    String desc;
    String enr_date;
    String due_date;
    boolean like;

    // 테스트를 위한 함수
    public void init(int i, String page) {
        title = page + " 챌린지 테스트" + i;
        desc = "챌린지 설명" + i;
        enr_date = "시작일" + i;
        due_date = "마감일" + i;
        like = false;
    }
}
