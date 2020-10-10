package com.example.temporal;

import retrofit2.Call;
import retrofit2.http.GET;

interface retrofitAPI {
    @GET("/challengeframe")
    Call<String> getChallengeframeList();

}