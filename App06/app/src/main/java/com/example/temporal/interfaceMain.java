package com.example.temporal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class interfaceMain extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_main);

        // 데이터베이스에서 ctgr 맞는거 불러오기
        aCurrentData.listWasteBanner.clear();
        for (int i = 0; i < 3; i++) {
            wasteItemBanner newOne = new wasteItemBanner();
            newOne.init(i);
            aCurrentData.listWasteBanner.add(newOne);
        }
        // 맞는 내 챌린지 불러오기
        aCurrentData.listMyChallenge.clear();
        for (int i = 0; i < 10; i++) {
            challengeItem newOne = new challengeItem();
            newOne.init(i, "나의 ");
            aCurrentData.listMyChallenge.add(newOne);
        }
        // 맞는 챌린지 불러오기
        aCurrentData.listChallenge.clear();
        for (int i = 0; i < 10; i++) {
            challengeItem newOne = new challengeItem();
            newOne.init(i, "남의 ");
            aCurrentData.listChallenge.add(newOne);
        }
        // 챌린지 등록 목록 불러오기
        aCurrentData.listChallengeEnroll.clear();
        for (int i = 0; i < 10; i++) {
            challengeEnrollItem newOne = new challengeEnrollItem();
            newOne.init(i);
            aCurrentData.listChallengeEnroll.add(newOne);
        }

        // 인터넷 연결 스레드
        //      펀딩 연결 -> 1초마다 새로고침
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                URL serverURL;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        serverURL = new URL("http://101.101.218.146:8080/funding");
                        System.out.println(serverURL);
                        HttpURLConnection myConnection = (HttpURLConnection) serverURL.openConnection();
                        myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                        if (myConnection.getResponseCode() == 200) {
                            System.out.println("펀딩 새로고침");
                        } else {
                            System.out.println("연결 실패!");
                        }
                        InputStream responseBody = myConnection.getInputStream();
                        aCurrentData.listFunding = readJsonStreamFunding(responseBody);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        TextView buttonHome = findViewById(R.id.menuHome);
        TextView buttonChallenge = findViewById(R.id.menuChallenge);
        TextView buttonFunding = findViewById(R.id.menuFunding);
        TextView buttonList = findViewById(R.id.menuList);
        TextView buttonInfo = findViewById(R.id.menuInfo);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        homeMain next = new homeMain();
        fragmentTransaction.replace(R.id.frameMain, next);
        fragmentTransaction.commit();

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (view.getId()) {
                    case R.id.menuHome:
                        fragmentTransaction.replace(R.id.frameMain, new homeMain());
                        break;
                    case R.id.menuChallenge:
                        fragmentTransaction.replace(R.id.frameMain, new challengeMain());
                        break;
                    case R.id.menuList:
                        fragmentTransaction.replace(R.id.frameMain, new listEnroll());
                        break;
                    case R.id.menuFunding:
                        fragmentTransaction.replace(R.id.frameMain, new fundingMain());
                        break;
                    case R.id.menuInfo:
                        fragmentTransaction.replace(R.id.frameMain, new infoMain());
                        break;
                }
                fragmentTransaction.addToBackStack(null).commit();
            }
        };
        buttonHome.setOnClickListener(btnListener);
        buttonChallenge.setOnClickListener(btnListener);
        buttonList.setOnClickListener(btnListener);
        buttonFunding.setOnClickListener(btnListener);
        buttonInfo.setOnClickListener(btnListener);
    }

    // 펀딩 리스트 서버에서 가져오기
    public ArrayList<fundingItem> readJsonStreamFunding(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readFundingArray(reader);
        } finally {
            reader.close();
        }
    }

    // 펀딩 리스트 서버에서 가져오기
    public ArrayList<fundingItem> readFundingArray(JsonReader reader) throws IOException {
        ArrayList<fundingItem> fundingItems = new ArrayList<fundingItem>();
        reader.beginArray();
        while (reader.hasNext()) {
            fundingItems.add(readFunding(reader));
        }
        reader.endArray();
        return fundingItems;
    }

    // 펀딩 리스트 서버에서 가져오기
    public fundingItem readFunding(JsonReader reader) throws IOException {
        fundingItem newOne = new fundingItem();
        reader.beginObject();

        while (reader.hasNext()) {
            /**
            String name = reader.nextName();
            if (name.equals("id")) {
                newOne.id = reader.nextInt();
            } else if (name.equals("title")) {
                newOne.title = reader.nextString();
            } else if (name.equals("startdate")) {
                newOne.regDate = reader.nextString();
            } else if (name.equals("enddate")) {
                newOne.closeDate = reader.nextString();
            } else if (name.equals("targetpoint")) {
                newOne.targetpoint = reader.nextInt();
            } else if (name.equals("description")) {
                newOne.descriptionClick = reader.nextString();
            } else {
                reader.skipValue();
            }
             */
        }
        reader.endObject();
        return newOne;
    }

    // 해당 엑티비티 내에서 프레그먼트 바꿀때 사용
    // 백의 자리: 하단 메뉴바 좌측에서부터 1, 2, 3, 4
    // 십 + 일의 자리: 각 메뉴 내부의 프레그먼트
    public void changeFragment(int usage) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (usage) {
            case 110: // 메인 챌린지 더보기 버튼 클릭시
                fragmentTransaction.replace(R.id.frameMain, new listMain());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 200: // 챌린지 화면 최초 클릭시
                fragmentTransaction.replace(R.id.frameChallengeMap, new challengeMap());
                fragmentTransaction.commit();
                break;
            case 400: // 펀딩 화면 최초 클릭시
                fragmentTransaction.replace(R.id.frameFundingList, new fundingList());
                fragmentTransaction.commit();
                changeFragmentFundingList();
                break;
            case 500: // 마이페이지 화면 최초 클릭시
                fragmentTransaction.replace(R.id.frameInfoList, new infoList());
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
    }

    // 홈의 분리배출법 리스트 불러오기
    // parameter : 스피너 값
    public void changeFragmentWasteBannerList() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        wasteItemBannerList newPage = new wasteItemBannerList();
        fragmentTransaction.replace(R.id.frameHomeWasteList, newPage);
        fragmentTransaction.commit();
    }

    public void changeFragmentWasteCtgr(String ctgr) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        wasteMain newPage = new wasteMain();
        newPage.setData(ctgr);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.commit();
    }

    // 펀딩 리스트 불러오기
    // parameter : 스피너 값
    public void changeFragmentFundingList() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fundingList newPage = new fundingList();
        fragmentTransaction.replace(R.id.frameFundingList, newPage);
        fragmentTransaction.commit();
    }

    // 분리배출법 세부페이지
    public void changeFragmentWasteItemSpecific(wasteItem newOne) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        wasteItemSpecific newPage = new wasteItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

//    // 펀딩 세부
//    public void changeFragmentFundingItemSpecific(fundingItem newOne) {
//        TextView txt = (TextView) findViewById(R.id.textDescription);
//        txt.setText(btn.getText());
//
//    }

//    // 펀딩 세부페이지
//    public void changeFragmentFundingItemSpecific(fundingItem newOne) {
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fundingItemSpecific newPage = new fundingItemSpecific();
//        newPage.setItem(newOne);
//        fragmentTransaction.replace(R.id.frameMain, newPage);
//        fragmentTransaction.addToBackStack(null).commit();
//    }

    // 챌린지 세부페이지 내꺼
    public void changeFragmentChallengeItemSpecific(challengeItem newOne) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeItemSpecific newPage = new challengeItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 챌린지 등록 세부페이지 내꺼
    public void changeFragmentChallengeEnrollItemSpecific(challengeEnrollItem newOne) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeEnrollItemSpecific newPage = new challengeEnrollItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 챌린지 세부페이지 남의 것
    public void changeFragmentChallengeItemSpecificOther(challengeItem newOne) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeItemSpecificOther newPage = new challengeItemSpecificOther();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    public void callCalander() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        calander newPage = new calander();
        fragmentTransaction.replace(R.id.frameCalander, newPage);
        fragmentTransaction.commit();
    }

    // 마이페이지
//    public void changeFragmentInfoList() {
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        infoList newPage = new infoList();
//        fragmentTransaction.replace(R.id.frameInfoList, newPage);
//        fragmentTransaction.commit();
//    }

    /** 사용 안함
     // 등록 프레그먼트의 체크리스트를 불러오기 위한 함수
     // parameter: 아직 생각 안해봄
     public void changeFragmentEnroll(enrollChecklistItem item) {
     FragmentManager fragmentManager = getFragmentManager();
     FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
     enrollChecklist newPage = new enrollChecklist();
     Bundle bd = new Bundle(2);
     bd.putString("ctgr", item.ctgr);
     bd.putStringArrayList("seq", item.seq);
     newPage.setArguments(bd);
     // paremeter를 이용해서 해당 카테고리에 맞는 체크리스트 call
     fragmentTransaction.replace(R.id.frameEnrollMain, newPage);
     fragmentTransaction.commit();
     }
     */

}
