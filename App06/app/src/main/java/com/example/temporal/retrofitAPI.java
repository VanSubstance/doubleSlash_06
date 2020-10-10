package com.example.temporal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface retrofitAPI {
    @GET("/challengeframe")
    Call<List<challengeFrameItem>> getChallengeframeList();
    @GET("/funding/all")
    Call<List<fundingItem>> getFundingList();
    @GET("/trash")
    Call<List<wasteItem>> getWasteList();
}