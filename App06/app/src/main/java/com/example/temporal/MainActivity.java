package com.example.temporal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ApiErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private gpsTracker gpsTracker;
    public double lon; //경도
    public double lat; //위도
    private SessionCallback sessionCallback;
    private retrofitAPI mRetrofitAPI;
    private Retrofit mRetrofit;
    private Call<List<challengeFrameItem>> mCallChallengeframeList;
    private Call<List<fundingItem>> mFundingItemList;
    private Call<List<wasteItem>> mWasteItemList;
    private Call<List<userItem>> mUserItemList;
    private Call<List<challengeItem>> mChallengeItemList;
    private Callback<List<challengeFrameItem>> mFrameCallback = new Callback<List<challengeFrameItem>>() {
        @Override
        public void onResponse(Call<List<challengeFrameItem>> call, Response<List<challengeFrameItem>> response) {
            for (challengeFrameItem item :
                    response.body()) {
                challengeItem newOne = new challengeItem();
                newOne.setFromFrame(item);
                aCurrentData.listChallengeEnroll.add(newOne);
            }

        }

        @Override
        public void onFailure(Call<List<challengeFrameItem>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    private Callback<List<userItem>> mUserCallback = new Callback<List<userItem>>() {
        @Override
        public void onResponse(Call<List<userItem>> call, Response<List<userItem>> response) {
            for (userItem item :
                    response.body()) {
                aCurrentData.listUser.add(item);
            }

        }

        @Override
        public void onFailure(Call<List<userItem>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    private Callback<List<fundingItem>> mFundingCallback = new Callback<List<fundingItem>>() {
        @Override
        public void onResponse(Call<List<fundingItem>> call, Response<List<fundingItem>> response) {
            for (fundingItem item :
                    response.body()) {
                aCurrentData.listFunding.add(item);
            }
        }

        @Override
        public void onFailure(Call<List<fundingItem>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    private Callback<List<wasteItem>> mWasteCallback = new Callback<List<wasteItem>>() {
        @Override
        public void onResponse(Call<List<wasteItem>> call, Response<List<wasteItem>> response) {
            for (wasteItem item :
                    response.body()) {
                aCurrentData.listWaste.add(item);
            }
        }

        @Override
        public void onFailure(Call<List<wasteItem>> call, Throwable t) {
            t.printStackTrace();
        }
    };
    private Callback<List<challengeItem>> mChallengeItemCallback = new Callback<List<challengeItem>>() {
        @Override
        public void onResponse(Call<List<challengeItem>> call, Response<List<challengeItem>> response) {
            for (challengeItem item :
                    response.body()) {
                aCurrentData.listMyChallenge.add(item);
            }
        }

        @Override
        public void onFailure(Call<List<challengeItem>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    private Callback<userItem> mUserPostCallback = new Callback<userItem>() {
        @Override
        public void onResponse(Call<userItem> call, Response<userItem> response) {
            System.out.println("성공");
            System.out.println(response);
            System.out.println(call);
        }
        @Override
        public void onFailure(Call<userItem> call, Throwable t) {
            t.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHashKey();

        gpsTracker = new gpsTracker(this);

        lat=gpsTracker.getLatitude();
        lon=gpsTracker.getLongitude();

        setRetrofitInit();
        callServer();

        Intent splash = new Intent(this, splashMain.class);
        startActivity(splash);

        final Intent intentSignin = new Intent(this, interfaceMain.class);

        sessionCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(sessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();

    }

    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://101.101.218.146:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(retrofitAPI.class);
    }

    private void callServer() {
        mCallChallengeframeList = mRetrofitAPI.getChallengeframeList();
        mFundingItemList = mRetrofitAPI.getFundingList();
        mWasteItemList = mRetrofitAPI.getWasteList();
        mUserItemList = mRetrofitAPI.getUserList();
        int id = aCurrentData.myInfo.id;
        mChallengeItemList = mRetrofitAPI.getChallengeList(id);
        mCallChallengeframeList.enqueue(mFrameCallback);
        mFundingItemList.enqueue(mFundingCallback);
        mWasteItemList.enqueue(mWasteCallback);
        mUserItemList.enqueue(mUserCallback);
        mChallengeItemList.enqueue(mChallengeItemCallback);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

    private class SessionCallback implements ISessionCallback {
        public double lon; //경도
        public double lat; //위도
        public void getLocation(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }
        @Override
        public void onSessionOpened() {
            UserManagement.getInstance().me(new MeV2ResponseCallback() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                    int result = errorResult.getErrorCode();

                    if(result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),"로그인 도중 오류가 발생했습니다: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Toast.makeText(getApplicationContext(),"세션이 닫혔습니다. 다시 시도해 주세요: "+errorResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(MeV2Response result) {
                    boolean exist = false;
                    Intent intent = new Intent(getApplicationContext(), interfaceMain.class);
                    for (userItem user :
                            aCurrentData.listUser) {
                        if (user.id == result.getId()) {
                            aCurrentData.myInfo = user;
                            startActivity(intent);
                            finish();
                            exist = true;
                            break;
                        }
                    }
                    if (!exist) {
                        userItem newOne = new userItem();
                        newOne.id = (int) result.getId();
                        newOne.nick = result.getNickname();
                        newOne.point = 0;
                        newOne.lon = lon;
                        newOne.lat = lat;
                        Call<userItem> userInfo = mRetrofitAPI.setUser(newOne);
                        userInfo.enqueue(mUserPostCallback);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
}


