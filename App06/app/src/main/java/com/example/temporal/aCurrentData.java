package com.example.temporal;

import net.daum.mf.map.api.MapPOIItem;

import java.util.ArrayList;

public class aCurrentData {
    public static ArrayList<wasteItem> listWaste = new ArrayList<wasteItem>();
    public static ArrayList<challengeItem> listChallenge = new ArrayList<challengeItem>();
    public static ArrayList<challengeItem> listMyChallenge = new ArrayList<challengeItem>();
    public static ArrayList<challengeItem> listChallengeEnroll = new ArrayList<challengeItem>();
    public static ArrayList<fundingItem> listFunding = new ArrayList<fundingItem>();
    public static ArrayList<wasteItemBanner> listWasteBanner = new ArrayList<wasteItemBanner>();
    public static ArrayList<userItem> listUser = new ArrayList<userItem>();
    public static ArrayList<userActivity> listActivity = new ArrayList<userActivity>();
    public static userItem myInfo = new userItem();
    // 다중 마커를 위해 만들었습니다.
    public static ArrayList<MapPOIItem> markerList=new ArrayList<>();
}
