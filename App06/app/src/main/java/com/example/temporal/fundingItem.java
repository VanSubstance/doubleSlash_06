package com.example.temporal;

public class fundingItem {
    String id;
    String title; // 기관 이름
    String desc; // 간략 설명
    String spec_desc; // 세부 설명
    int accu_point; // 펀딩 전체에서 누적 포인트
    int targ_point; // 펀딩 목표 포인트
    int left_point; // 펀딩 전체에서 달성까지 남은 포인트

    public void init(int i) {
        title = "그린피스" + i;
        desc = "펀딩 제목" + i;
        spec_desc = "펀딩 세부내용 문구" + i;
        accu_point = 0;
        targ_point = 1000;
        left_point = targ_point - accu_point;
    }

}

