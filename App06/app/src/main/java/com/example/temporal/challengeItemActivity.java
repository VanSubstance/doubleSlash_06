package com.example.temporal;

public class challengeItemActivity {
    int chalId;
    String img;

    public void clone(challengeItemActivity original) {
        chalId = original.chalId;
        img = original.img;
    }
}

