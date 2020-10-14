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

    @POST("funding_activity/{fund_id}")
    Call<Boolean> postFundingActivity(@Path("fund_id") int fund_id, @Body fundingItemActivity newOne);

    @GET("/trash")
    Call<List<wasteItem>> getWasteList();

    @GET("/member")
    Call<List<userItem>> getUserList();
    @POST("/member")
    Call<userItem> setUser(@Body userItem post);
    @PUT("member/location/{memId}")
    Call<location> putLocation(@Path("memId") int memId, @Body location newOne);
    @PUT("member/point/{memId}")
    Call<point> changePoint(@Path("memId") int memId, @Body point newOne);

    @POST("/challenge")
    Call<challengeItemForPost> setChallenge(@Body challengeItemForPost post);
    @GET("/challenge/{memId}")
    Call<List<challengeItem>> getChallengeList(@Path("memId") int memId);
    @DELETE("/challenge/{chalId}")
    Call<Void> deleteChallengeItem(@Path("chalId") int chalId);



    @POST("/challengeactivity")
    Call<challengeItemActivity> postChallengeActivity(@Body challengeItemActivity newOne);
    @GET("/challengeactivity/actlist/{chalId}")
    Call<List<challengeItemActivityForGet>> getChallengeActivityList(@Path("chalId") int chalId);

}