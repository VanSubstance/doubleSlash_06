package com.example.temporal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    @POST("/challenge")
    Call<challengeItemForPost> setChallenge(@Body challengeItemForPost post);
    @GET("/challenge/{id}")
    Call<List<challengeItem>> getChallengeList(@Path("id") int id);
}