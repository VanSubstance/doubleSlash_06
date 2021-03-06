package com.example.temporal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ApiErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final int GPS_ENABLE_REQUEST_CODE = 2000;
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int PERMISSIONS_REQUEST_CODE = 100;

    public String addr;

    private gpsTracker gpsTracker;
    public double lon; //경도
    public double lat; //위도
    private SessionCallback sessionCallback;
    private retrofitAPI mRetrofitAPI;
    private Retrofit mRetrofit;
    private Call<List<challengeItem>> mChallengeItemList;
    private Call<Boolean> mLocation;
    Call<Boolean> userInfo;
    private Call<List<challengeFrameItem>> mCallChallengeframeList;
    private Call<List<fundingItem>> mFundingItemList;
    private Call<List<userItem>> mUserItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHashKey();
        /*
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash = new Intent(MainActivity.this, splashMain.class);
                startActivity(splash);
                finish();
            }
        }, 1500);

         */

        gpsTracker = new gpsTracker(this);

        lat = gpsTracker.getLatitude();
        lon = gpsTracker.getLongitude();
        addr = getCurrentAddress(lat, lon);

        setRetrofitInit();
        callServer();

        sessionCallback = new SessionCallback();
        sessionCallback.getLocation(lat, lon);
        Session.getCurrentSession().addCallback(sessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    // map
    public String getCurrentAddress(double latitude, double longitude) {
        //지오코더 ..GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    100);
        } catch (IOException e) {
            //네트워크 문제
            Toast.makeText(this, "지오코터 서비스 사용불가", Toast.LENGTH_LONG).show();
            showDialogForLocationServiceSetting();
            return "잘못된 GPS 좌표";
        }
        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            showDialogForLocationServiceSetting();
            return "주소 미발견";
        }
        Address address = addresses.get(0);
        return address.getAddressLine(0).toString();
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n" + "위치 설정을 수정할래용?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent =
                        new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    public void checkGPSPermission() {
        int locationPermissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int coarsePermissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (locationPermissionCheck == PackageManager.PERMISSION_DENIED && coarsePermissionCheck == PackageManager.PERMISSION_DENIED) {
            Log.d("permissionCheck", "denied");
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
        } else if (locationPermissionCheck == PackageManager.PERMISSION_GRANTED) {
            Log.d("permissionCheck", "granted");
        }
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
        mUserItemList = mRetrofitAPI.getUserList();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (challengeFrameItem item :
                            mCallChallengeframeList.execute().body()) {
                        challengeItem newOne = new challengeItem();
                        newOne.setFromFrame(item);
                        aCurrentData.listChallengeEnroll.add(newOne);
                    }
                    for (fundingItem item :
                            mFundingItemList.execute().body()) {
                        aCurrentData.listFunding.add(item);
                    }
                    for (userItem item :
                            mUserItemList.execute().body()) {
                        aCurrentData.listUser.add(item);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case GPS_ENABLE_REQUEST_CODE: //사용자가 GPS 활성 시켰는지 검사
                    if (checkLocationServicesStatus()) {
                        if (checkLocationServicesStatus()) {
                            Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                            checkGPSPermission();
                            return;
                        }
                    }
                    break;
            }
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

                    if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(getApplicationContext(), "네트워크 연결이 불안정합니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다: " + errorResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Toast.makeText(getApplicationContext(), "세션이 닫혔습니다. 다시 시도해 주세요: " + errorResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(MeV2Response result) {
                    boolean exist = false;
                    for (userItem user :
                            aCurrentData.listUser) {
                        if (user.id == result.getId()) {
                            aCurrentData.myInfo = user;
                            location newOne = new location();
                            aCurrentData.myInfo.lat = lat;
                            aCurrentData.myInfo.lon = lon;
                            newOne.lat = lat;
                            newOne.lon = lon;
                            int id = aCurrentData.myInfo.id;
                            mLocation = mRetrofitAPI.putLocation(id, newOne);
                            mChallengeItemList = mRetrofitAPI.getChallengeList(id);
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        for (challengeItem item :
                                                mChallengeItemList.execute().body()) {
                                            item.setDatesFromServer();
                                            aCurrentData.listMyChallenge.add(item);
                                        }
                                        mLocation.execute();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            thread.start();
                            try {
                                thread.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
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
                        userInfo = mRetrofitAPI.setUser(newOne);
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    userInfo.execute();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Intent intent = new Intent(getApplicationContext(), interfaceMain.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException e) {
            Toast.makeText(getApplicationContext(), "로그인 도중 오류가 발생했습니다. 인터넷 연결을 확인해주세요: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void getHashKey() {
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


