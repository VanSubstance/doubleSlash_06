package com.example.temporal;

public class challengeItemActivityForGet {
    int chalactId;
    int chalId;
    String regdate;
    String img;
    int cnt;

    public void clone(challengeItemActivityForGet original) {
        chalactId = original.chalactId;
        chalId = original.chalId;
        regdate = original.regdate;
        img = original.img;
        cnt = original.cnt;
    }
}
