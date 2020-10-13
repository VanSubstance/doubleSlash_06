package com.example.temporal;

public class challengeItemActivity {
    int chalId;
    String img;

    public void clone(challengeItemActivity original) {
        chalId = original.chalId;
        img = original.img;
    }

    public void setFromDb(challengeItemActivityForGet original) {
        chalId = original.chalId;
        img = original.img;
    }
}

