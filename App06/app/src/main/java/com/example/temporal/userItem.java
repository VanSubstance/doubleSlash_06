package com.example.temporal;

import java.util.ArrayList;

public class userItem {
    String id;
    String nic;
    String title;
    String enr_date;
    String ctr = "0";
    int targ_point;
    int left_point;
    Double log;
    Double lat;

    // 테스트를 위한 함수
    public void init(int i, String page) {
        title = page + "제목" + i;
        enr_date = "시작일" + i;

        targ_point = 500;
        //targ_point = -500;
        left_point = left_point + targ_point;
        // targ_point이 양수이면 챌린지
        if(targ_point > 0)
        {
            ctr = "챌린지";

        }
        // targ_point이 음수이면 펀딩
        else if(targ_point < 0)
        {
            ctr = "펀딩";
        }

    }


}
