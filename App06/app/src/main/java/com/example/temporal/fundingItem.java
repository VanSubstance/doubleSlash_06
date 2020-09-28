package com.example.temporal;

public class fundingItem {
    String id;
    String title; // 기관 이름
    String desc; // 간략 설명
    String spec_desc; // 세부 설명
    String accu_point; // 펀딩 전체에서 누적 포인트
    String targ_point; // 펀딩 목표 포인트
    String left_point; // 펀딩 전체에서 달성까지 남은 포인트

    public void init(int i) {
        fundingName = "그린피스" + i;
        fundingTitle = "펀딩 제목" + i;
        fundingSpecific = "펀딩 세부내용 문구" + i;
        descriptionClick = "펀딩 설명" + i;
        specific_title = "펀딩 세부내용 제목" + i;
        specific_description = "펀딩 세부내용" + i;
        nowPoint = "나의 현재 포인트" + i;
        seekmin = "0_" + i;
        seekmax = "5000(현재 포인트)_" + i;
        point = "펀딩 포인트" + i;
        restPoint = "잔여 포인트" + i;
    }

}

