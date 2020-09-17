package com.example.temporal;

public class wasteItem {
    String id;
    String title;
    String description;
    String url;
    String ctgr;
    // 컨텐츠는 String? 아니면 Array<String>?
    String content;
    public void init(int i) {
        id = "wasteID_" + i;
        title = "분리수거법 테스트" + i;
        description = "분리수거법 설명" + i;
        url = "사진" + i;
        ctgr = "카테고리" + i;
    }
}
