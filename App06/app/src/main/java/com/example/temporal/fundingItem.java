package com.example.temporal;

public class fundingItem {
    String id;
    String inst; // 기관 이름
//    Sting inst_icon;
    String inst_desc; // 기관 설명
    int seek_max;
    int tar_point; // 펀딩 목표 포인트
    int acu_point; // 펀딩 전체에서 누적 포인트
    int left_point; // 펀딩 전체에서 달성까지 남은 포인트

    public void init(int i) {
        inst = "그린피스" + i;
        inst_desc = "기관 세부내용 문구" + i;
        tar_point = 10000000;
        acu_point = 9000000;
        left_point = tar_point - acu_point;
    }

}

