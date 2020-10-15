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

    @POST("funding_activity")
    Call<Boolean> postFundingActivity(@Body fundingItemActivity newOne);
    @GET("funding_activity_mem/{mem_id}")
    Call<List<fundingItemActivityForGet>> getFundingActivityListByUser(@Path("mem_id") int mem_id);

    @GET("/trash")
    Call<List<wasteItem>> getWasteList();

    @GET("/member")
    Call<List<userItem>> getUserList();
    @POST("/member")
    Call<Boolean> setUser(@Body userItem post);
    @PUT("member/location/{memId}")
    Call<Boolean> putLocation(@Path("memId") int memId, @Body location newOne);
    @PUT("member/point/{memId}")
    Call<Boolean> changePoint(@Path("memId") int memId, @Body point newOne);

    @POST("/challenge")
    Call<Boolean> setChallenge(@Body challengeItemForPost post);
    @GET("/challenge/completed/{memId}")
    Call<List<challengeItem>> getCompletedChallengeList(@Path("memId") int memId);
    @GET("/challenge/{memId}")
    Call<List<challengeItem>> getChallengeList(@Path("memId") int memId);
    @DELETE("/challenge/{chalId}")
    Call<Void> deleteChallengeItem(@Path("chalId") int chalId);
    @PUT("challenge/completed/{chalId}")
    Call<Boolean> putChallengeComplete(@Path("chalId") int chalId, @Body int completed);



    @POST("/challengeactivity")
    Call<Boolean> postChallengeActivity(@Body challengeItemActivity newOne);
    @GET("/challengeactivity/actlist/{chalId}")
    Call<List<challengeItemActivityForGet>> getChallengeActivityList(@Path("chalId") int chalId);
    @GET("challengeactivity/{memId}")
    Call<List<challengeItemActivityForGet>> getChallengeActivityListByUser(@Path("memId") int memId);
    @DELETE("/challengeactivity/{chalId}")
    Call<Void> deleteChallengeActivities(@Path("chalId") int chalId);

}