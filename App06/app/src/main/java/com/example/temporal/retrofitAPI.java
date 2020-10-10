package com.example.temporal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

interface retrofitAPI {
    @GET("/challengeframe")
    Call<List<challengeFrameItem>> getChallengeframeList();
    @GET("/funding/all")
    Call<List<fundingItem>> getFundingList();
    @GET("/trash")
    Call<List<wasteItem>> getWasteList();
    @GET("/member")
    Call<List<userItem>> getUserList();
    @POST("/member")
    Call<userItem> setUser(@Body userItem post);
}