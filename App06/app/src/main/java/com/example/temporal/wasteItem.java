package com.example.temporal;

public class wasteItem {
    String id;
    String title;
    String desc;
    String url;
    String ctgr;
    // 컨텐츠는 String? 아니면 Array<String>?
    String spec_ctgr;
    String content;
    public void init(int i) {
        id = "wasteID_" + i;
        title = id +" 테스트" + i;
        desc = id + " 분리수거법 " + i;
        url = "사진" + i;
        ctgr ="카테고리" + i;
        spec_ctgr = "세부 카테고리" + i;
    }
    public void setCtgr(String ctgr) {
        this.ctgr = ctgr;
        this.ctgr = ctgr + " 세부 카테고리";
    }
}
