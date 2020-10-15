package com.example.temporal;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class challengeMain extends Fragment implements OnItemClickForChallenge, MapView.MapViewEventListener {
    TextView textCount;
    // 챌린지 목록 -> 등록순으로 보여주기
    private retrofitAPI mRetrofitAPI;
    private Retrofit mRetrofit;
    private Call<List<challengeItem>> mChallengeItemList;
    private Call<List<challengeItemActivityForGet>> mCallActivities;

    private MapView mapView;
    private gpsTracker gpsTracker;
    public double lon; //경도
    public double lat; //위도
    private NestedScrollView scrollView;
    RecyclerView viewList;
    challengeItemAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.challenge_main, container, false);
        setRetrofitInit();

        gpsTracker = new gpsTracker(getContext());
        lat=gpsTracker.getLatitude();
        lon=gpsTracker.getLongitude();
        initView(view);

        ImageView lc = (ImageView) view.findViewById(R.id.current_button);
        lc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(lat, lon), true);
            }
        });
        textCount = view.findViewById(R.id.todayCount);


        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new challengeItemAdapter(aCurrentData.listMyChallenge, this);
        textCount.setText(aCurrentData.listMyChallenge.size() + " 개");
        viewList.setAdapter(adapter);
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new challengeItemSwipeController(adapter));
        itemTouchhelper.attachToRecyclerView(viewList);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        scrollView = view.findViewById(R.id.viewScroll);
        final Button buttonDownward = view.findViewById(R.id.buttonDownward);
        final Button buttonUpward = view.findViewById(R.id.buttonUpward);
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
        buttonUpward.setVisibility(View.GONE);
        buttonDownward.setVisibility(View.VISIBLE);

        return view;
    }

    @Override
    public void onClick(challengeItem newOne) {
        boolean mychallenge = false;
        for (challengeItem mine : aCurrentData.listMyChallenge) {
            if (newOne.chalId == mine.chalId) {
                mychallenge = true;
                break;
            }
        }
        if (mychallenge) {
            ((interfaceMain)getActivity()).changeFragmentChallengeItemSpecific(newOne);
        } else {
            ((interfaceMain) getActivity()).changeFragmentChallengeItemSpecificOther(newOne);
        }
    }

    private void setRetrofitInit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://101.101.218.146:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitAPI = mRetrofit.create(retrofitAPI.class);
    }

    MapView.POIItemEventListener event = new MapView.POIItemEventListener() {
        @Override
        public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
            mChallengeItemList = mRetrofitAPI.getChallengeList(mapPOIItem.getTag());
            if (mapPOIItem.getTag() == aCurrentData.myInfo.id) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        aCurrentData.listMyChallenge.clear();
                        try {
                            for (challengeItem item :
                                    mChallengeItemList.execute().body()) {
                                item.setDatesFromServer();
                                aCurrentData.listMyChallenge.add(item);
                            }
                            for(challengeItem one : aCurrentData.listMyChallenge) {
                                mCallActivities = mRetrofitAPI.getChallengeActivityList(one.chalId);
                                List<challengeItemActivityForGet> original = null;
                                try {
                                    original = mCallActivities.execute().body();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (original == null || original.size() == 0) {
                                } else {
                                    one.progress = original.size();
                                    int emptyLength = one.acvts.size() - original.size();
                                    one.acvts.clear();
                                    for (challengeItemActivityForGet items : original) {
                                        challengeItemActivity newOne = new challengeItemActivity();
                                        newOne.setFromDb(items);
                                        one.acvts.add(newOne);
                                    }
                                    for (int i = 0; i < emptyLength; i++) {
                                        challengeItemActivity newOne = new challengeItemActivity();
                                        one.acvts.add(newOne);
                                    }
                                }
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
                textCount.setText(aCurrentData.listMyChallenge.size() + " 개");
                adapter.changeData(aCurrentData.listMyChallenge);
                viewList.removeAllViewsInLayout();
                viewList.setAdapter(adapter);
            }
            else {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        aCurrentData.listChallenge.clear();
                        try {
                            for (challengeItem item :
                                    mChallengeItemList.execute().body()) {
                                item.setDatesFromServer();
                                aCurrentData.listChallenge.add(item);
                            }
                            for(challengeItem one : aCurrentData.listChallenge) {
                                mCallActivities = mRetrofitAPI.getChallengeActivityList(one.chalId);
                                List<challengeItemActivityForGet> original = null;
                                try {
                                    original = mCallActivities.execute().body();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (original == null || original.size() == 0) {
                                } else {
                                    one.progress = original.size();
                                    int emptyLength = one.acvts.size() - original.size();
                                    one.acvts.clear();
                                    for (challengeItemActivityForGet items : original) {
                                        challengeItemActivity newOne = new challengeItemActivity();
                                        newOne.setFromDb(items);
                                        one.acvts.add(newOne);
                                    }
                                    for (int i = 0; i < emptyLength; i++) {
                                        challengeItemActivity newOne = new challengeItemActivity();
                                        one.acvts.add(newOne);
                                    }
                                }
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
                textCount.setText(aCurrentData.listChallenge.size() + " 개");
                adapter.changeData(aCurrentData.listChallenge);
                viewList.removeAllViewsInLayout();
                viewList.setAdapter(adapter);
            }
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
    };

    private void initView(final View view) {
        mapView = new MapView(getContext());
        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        mapView.setMapViewEventListener(this);
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(lat, lon), 3, true);
        mapView.zoomIn(true);
        mapView.zoomOut(true);
        mapView.setPOIItemEventListener(event);
        //구문을 사용할 예정 !!
        for (userItem item : aCurrentData.listUser) {
            MapPOIItem mapPOIItem = new MapPOIItem();
            mapPOIItem.setItemName(item.nick);
            mapPOIItem.setTag(item.id);
            mapPOIItem.setMapPoint(MapPoint.mapPointWithGeoCoord(item.lat, item.lon));
            mapPOIItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            mapPOIItem.setCustomImageResourceId(R.drawable.marker); // 마커 이미지.
            // 기본으로 제공하는 BluePin 마커 모양.
            mapPOIItem.setSelectedMarkerType(MapPOIItem.MarkerType.CustomImage);
            mapPOIItem.setCustomSelectedImageResourceId(R.drawable.marker_s);
            mapView.addPOIItem(mapPOIItem);
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
