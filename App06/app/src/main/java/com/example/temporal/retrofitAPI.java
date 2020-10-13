package com.example.temporal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    @PUT("/location/{mem_id}")
    Call<location> putLocation(@Path("mem_id") int mem_id, @Body location newOne);
    @POST("funding_activity/{fund_id}")
    Call<fundingItemActivity> postFundingActivity(@Path("fund_id") int fund_id, @Body fundingItemActivity newOne);
    @PUT("member/point/{memId}")
    Call<point> putFundingActivity(@Path("memId") int memId, @Body point newOne);
    @POST("/challengeactivity")
    Call<challengeItemActivity> postChallengeActivity(@Body challengeItemActivity newOne);
    @GET("/challengeactivity/{memId}")
    Call<List<challengeItemActivity>> getChallengeActivityList(@Path("memId") int memId);
    @DELETE("/challenge/{chalId}")
    Call<Void> deleteChallengeItem(@Path("chalId") int chalId);
}