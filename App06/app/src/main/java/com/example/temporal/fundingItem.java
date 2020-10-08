package com.example.temporal;

public class fundingItem {
    String id;
    String inst; // 기관 이름
//    Sting inst_icon;
    String inst_desc; // 기관 설명
    int seek_max;
    int accu_point; // 펀딩 전체에서 누적 포인트
    int targ_point; // 펀딩 목표 포인트
    int left_point; // 펀딩 전체에서 달성까지 남은 포인트

    public void init(int i) {
        inst = "그린피스" + i;
        inst_desc = "기관 세부내용 문구" + i;
        targ_point = 10000000;
        accu_point = 9000000;
        left_point = targ_point - accu_point;
    }

}

