package com.example.temporal;

import java.util.ArrayList;

public class userItem {
    String id;
    String nic;
    String title;
    String enr_date;
    String point;
    String left_point;
    Double log;
    Double lat;

    // 테스트를 위한 함수
    public void init(int i, String page) {
        title = page + "제목" + i;
        enr_date = "시작일" + i;
        point = "+/- 포인트" + i;
        left_point = "잔액 포인트" + i;

    }


}
