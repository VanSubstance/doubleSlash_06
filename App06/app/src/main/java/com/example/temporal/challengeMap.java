
package com.example.temporal;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class challengeMap extends Fragment {

    private static final int GPS_ENABLE_REQUEST_CODE = 2000;
    private static final String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    // 중요...
    // 지도 구현
    private MapView mapView;

    public double longitude; //경도
    public double latitude; //위도
    private gpsTracker gpsTracker;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_map, container,false);

        // test
        TextView tv=(TextView)view.findViewById(R.id.textView2);
        
        gpsTracker = new gpsTracker(getContext());
        latitude=gpsTracker.getLatitude();
        longitude=gpsTracker.getLongitude();
        String address=getCurrentAddress(latitude,longitude);
        tv.setText(address);
        initView(view);

        final Button lc = view.findViewById(R.id.current_button);
        lc.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {

                                  }
                              }

        );
        return view;
    }

    public String getCurrentAddress( double latitude, double longitude){
        //지오코더 ..GPS를 주소로 변환
        Geocoder geocoder=new Geocoder(getContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    100);
        } catch (IOException e) {
            //네트워크 문제
            Toast.makeText(getContext(),"지오코터 서비스 사용불가",Toast.LENGTH_LONG).show();
            showDialogForLocationServiceSetting();
            return "잘못된 GPS 좌표";
        }
        if(addresses==null||addresses.size()==0){
            Toast.makeText(getContext(),"주소 미발견",Toast.LENGTH_LONG).show();
            showDialogForLocationServiceSetting();
            return "주소 미발견";
        }
        Address address=addresses.get(0);
        return address.getAddressLine(0).toString();
    }
    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager)getActivity().getSystemService(getContext().LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"+"위치 설정을 수정할래용?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent =
                        new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
    }
    public void checkGPSPermission(){
        int locationPermissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        int coarsePermissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        if(locationPermissionCheck == PackageManager.PERMISSION_DENIED && coarsePermissionCheck == PackageManager.PERMISSION_DENIED){
            Log.d("permissionCheck", "denied");
            ActivityCompat.requestPermissions(getActivity(),  REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
        }
        else if(locationPermissionCheck == PackageManager.PERMISSION_GRANTED){
            Log.d("permissionCheck", "granted");
        }
    }

    private void initView(View view) {
        mapView= new MapView(getContext());
        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
    }
 }