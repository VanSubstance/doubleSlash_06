package com.example.temporal;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class challengeMain extends Fragment implements OnItemClickForChallenge, MapView.MapViewEventListener {
    // 챌린지 목록 -> 등록순으로 보여주기

    //지도
    private static final int GPS_ENABLE_REQUEST_CODE = 2000;
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int PERMISSIONS_REQUEST_CODE = 100;

    private MapView mapView;
    private gpsTracker gpsTracker;
    public double lon; //경도
    public double lat; //위도
    private NestedScrollView scrollView;
    public MapPOIItem mapPOIItem;

    RecyclerView viewList;
    challengeItemAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.challenge_main, container, false);

        //지도
        // test

        gpsTracker = new gpsTracker(getContext());
        lat=gpsTracker.getLatitude();
        lon=gpsTracker.getLongitude();
        initView(view);

        final Button lc = view.findViewById(R.id.current_button);
        lc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lon), true);
            }
        });


        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new challengeItemAdapter(aCurrentData.listChallenge, this);
        viewList.setAdapter(adapter);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        scrollView = view.findViewById(R.id.viewScroll);
        final Button buttonDownward = view.findViewById(R.id.buttonDownward);
        final Button buttonUpward = view.findViewById(R.id.buttonUpward);
        buttonUpward.setVisibility(View.GONE);
        buttonDownward.setVisibility(View.VISIBLE);
        buttonDownward.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.fullScroll(View.FOCUS_DOWN);
                buttonDownward.setVisibility(View.GONE);
                buttonUpward.setVisibility(View.VISIBLE);
            }
        });
        buttonUpward.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.fullScroll(View.FOCUS_UP);
                buttonUpward.setVisibility(View.GONE);
                buttonDownward.setVisibility(View.VISIBLE);
            }
        });
        scrollView.setOnTouchListener(new NestedScrollView.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (scrollView.getScrollY() >= 0 && scrollView.getScrollY() < 400) {
                    buttonDownward.setVisibility(View.VISIBLE);
                    buttonUpward.setVisibility(View.GONE);
                } else {
                    buttonUpward.setVisibility(View.VISIBLE);
                    buttonDownward.setVisibility(View.GONE);
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onClick(challengeItem newOne) {
        ((interfaceMain) getActivity()).changeFragmentChallengeItemSpecificOther(newOne);
    }



    private void initView(View view) {
        mapView = new MapView(getContext());
        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setMapViewEventListener(this);
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(lat, lon), 3, true);
        mapView.zoomIn(true);
        mapView.zoomOut(true);

        //구문을 사용할 예정 !!
        for (userItem item : aCurrentData.listUser) {
            mapPOIItem = new MapPOIItem();
            mapPOIItem.setItemName("");
            mapPOIItem.setTag(0);
            mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(item.lat, item.lon));
            mapPOIItem.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
            mapPOIItem.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 Respin 마커 모양.
            mapView.addPOIItem(mapPOIItem);
            mapView.setPOIItemEventListener(new MapView.POIItemEventListener() {
                @Override
                public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

                }

                @Override
                public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

                }

                @Override
                public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

                }

                @Override
                public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

                }
            });
        }
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        scrollView.requestDisallowInterceptTouchEvent(true);

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
        scrollView.requestDisallowInterceptTouchEvent(true);
    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        scrollView.requestDisallowInterceptTouchEvent(true);
    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        scrollView.requestDisallowInterceptTouchEvent(true);
    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
        scrollView.requestDisallowInterceptTouchEvent(true);
    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        scrollView.requestDisallowInterceptTouchEvent(true);
    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
        scrollView.requestDisallowInterceptTouchEvent(true);
    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        scrollView.requestDisallowInterceptTouchEvent(true);

    }
}
