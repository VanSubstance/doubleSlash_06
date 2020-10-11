package com.example.temporal;

import java.util.ArrayList;

import static java.lang.Math.min;

public class fundingItem {
    int fund_id;
    String fund_inst;
    String inst_icon;
    String inst_des;
    int seek_max;
    int tar_point; // 펀딩 목표 포인트
    int acu_point; // 펀딩 전체에서 누적 포인트
    int left_point; // 펀딩 전체에서 달성까지 남은 포인트
    int fund_point;
    int rest_point;

    public void init(int i) {
        fund_inst = "그린피스" + i;
        inst_des = "기관 세부내용 문구" + i;

        tar_point = 10000000;
        acu_point = 9000000;


        left_point = tar_point - acu_point;

        seek_max = min(aCurrentData.myInfo.point, left_point);
        fund_point = 0;
        rest_point = aCurrentData.myInfo.point;
    }

}

