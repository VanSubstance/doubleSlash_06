package com.example.temporal;

public class wasteItem {
    String id;
    String title;
    String des;
    String img;
    String ctgr;
    // 컨텐츠는 String? 아니면 Array<String>?
    String spec_ctgr;
    String content;
    public void setCtgr(String ctgr) {
        this.ctgr = ctgr;
        this.ctgr = ctgr + " 세부 카테고리";
    }
}
