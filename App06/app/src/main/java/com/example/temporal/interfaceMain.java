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
    FragmentManager fragmentManager = getFragmentManager();

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
        /**
         // 맞는 내 챌린지 불러오기
         aCurrentData.listMyChallenge.clear();
         for (int i = 0; i < 4; i++) {
         challengeItem newOne = new challengeItem();
         newOne.init(i, "나의 ");
         aCurrentData.listMyChallenge.add(newOne);
         }
         // 맞는 챌린지 불러오기
         aCurrentData.listChallenge.clear();
         for (int i = 0; i < 4; i++) {
         challengeItem newOne = new challengeItem();
         newOne.init(i, "남의 ");
         aCurrentData.listChallenge.add(newOne);
         }
         */
        // 분리배출법 서버 연결
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                URL serverURL;
                String baseIP = "http://101.101.218.146:8080/";
                HttpURLConnection myConnection;
                InputStream responseBody;
                boolean conFailWaste = true;
                while (conFailWaste) {
                    try {
                        String linkTrash = baseIP + "trash";
                        serverURL = new URL(linkTrash);
                        System.out.println(serverURL);
                        myConnection = (HttpURLConnection) serverURL.openConnection();
                        myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                        if (myConnection.getResponseCode() == 200) {
                            System.out.println("분리배출법 연결");
                            conFailWaste = false;
                            responseBody = myConnection.getInputStream();
                            aCurrentData.listWaste = readJsonStreamWaste(responseBody);
                        } else {
                            System.out.println("분리배출법 연결 실패!");
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // 챌린지 틀 서버 연결
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                URL serverURL;
                String baseIP = "http://101.101.218.146:8080/";
                HttpURLConnection myConnection;
                InputStream responseBody;
                boolean conFailChallEnroll = true;
                while (conFailChallEnroll) {
                    try {
                        String linkChallengeEnroll = baseIP + "challengeframe";
                        serverURL = new URL(linkChallengeEnroll);
                        System.out.println(serverURL);
                        myConnection = (HttpURLConnection) serverURL.openConnection();
                        myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                        if (myConnection.getResponseCode() == 200) {
                            System.out.println("챌린지 틀 연결");
                            conFailChallEnroll = false;
                            responseBody = myConnection.getInputStream();
                            aCurrentData.listChallengeEnroll = readJsonStreamChallenge(responseBody);
                        } else {
                            System.out.println("챌린지 틀 연결 실패!");
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                URL serverURL;
                String baseIP = "http://101.101.218.146:8080/";
                HttpURLConnection myConnection;
                InputStream responseBody;
                boolean conFailFunding = true;
                while (conFailFunding) {
                    try {
                        String linkFunding = baseIP + "funding/all";
                        serverURL = new URL(linkFunding);
                        System.out.println(serverURL);
                        myConnection = (HttpURLConnection) serverURL.openConnection();
                        myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                        if (myConnection.getResponseCode() == 200) {
                            System.out.println("펀딩 연결");
                            conFailFunding = false;
                            responseBody = myConnection.getInputStream();
                            aCurrentData.listFunding = readJsonStreamFunding(responseBody);
                        } else {
                            System.out.println("펀딩 연결 실패!");
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
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
        fragmentTransaction.addToBackStack(null).commit();

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callMenu(view.getId());
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

    public ArrayList<fundingItem> readFundingArray(JsonReader reader) throws IOException {
        ArrayList<fundingItem> fundingItems = new ArrayList<fundingItem>();
        reader.beginArray();
        while (reader.hasNext()) {
            fundingItems.add(readFunding(reader));
        }
        reader.endArray();
        return fundingItems;
    }

    public fundingItem readFunding(JsonReader reader) throws IOException {
        fundingItem newOne = new fundingItem();
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("fund_id")) {
                newOne.id = reader.nextString();
            } else if (name.equals("fund_inst")) {
                newOne.title = reader.nextString();
            } else if (name.equals("inst_des")) {
                newOne.desc = reader.nextString();
            } else if (name.equals("fund_img")) {
                newOne.spec_desc = reader.nextString();
            } else if (name.equals("targetpoint")) {
                newOne.targ_point = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return newOne;
    }

    // 분리배출법 리스트 서버에서 가져오기
    public ArrayList<wasteItem> readJsonStreamWaste(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readWasteArray(reader);
        } finally {
            reader.close();
        }
    }

    public ArrayList<wasteItem> readWasteArray(JsonReader reader) throws IOException {
        ArrayList<wasteItem> wasteItems = new ArrayList<wasteItem>();
        reader.beginArray();
        while (reader.hasNext()) {
            wasteItems.add(readWaste(reader));
        }
        reader.endArray();
        return wasteItems;
    }

    public wasteItem readWaste(JsonReader reader) throws IOException {
        wasteItem newOne = new wasteItem();
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                newOne.id = reader.nextString();
            } else if (name.equals("title")) {
                newOne.title = reader.nextString();
            } else if (name.equals("img")) {
                newOne.url = reader.nextString();
            } else if (name.equals("des")) {
                newOne.desc = reader.nextString();
            } else if (name.equals("ctgr")) {
                newOne.ctgr = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return newOne;
    }

    // 챌린지 틀 리스트 서버에서 가져오기
    public ArrayList<challengeItem> readJsonStreamChallenge(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readChallengeArray(reader);
        } finally {
            reader.close();
        }
    }

    public ArrayList<challengeItem> readChallengeArray(JsonReader reader) throws IOException {
        ArrayList<challengeItem> challengeItems = new ArrayList<challengeItem>();
        reader.beginArray();
        while (reader.hasNext()) {
            challengeItems.add(readChallenge(reader));
        }
        reader.endArray();
        return challengeItems;
    }

    public challengeItem readChallenge(JsonReader reader) throws IOException {
        challengeItem newOne = new challengeItem();
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("TITLE")) {
                newOne.title = reader.nextString();
            } else if (name.equals("DES")) {
                newOne.desc = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return newOne;
    }

    public void callMenu(int id) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id) {
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

    // 해당 엑티비티 내에서 프레그먼트 바꿀때 사용
    // 백의 자리: 하단 메뉴바 좌측에서부터 1, 2, 3, 4
    // 십 + 일의 자리: 각 메뉴 내부의 프레그먼트
    public void changeFragment(int usage) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (usage) {
            case 110: // 메인 챌린지 더보기 버튼 클릭시
                fragmentTransaction.replace(R.id.frameMain, new listMain());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 400: // 펀딩 화면 최초 클릭시
                fragmentTransaction.replace(R.id.frameFundingList, new fundingList());
                fragmentTransaction.addToBackStack(null).commit();
                changeFragmentFundingList();
                break;
            case 500: // 마이페이지 화면 최초 클릭시
                fragmentTransaction.replace(R.id.frameInfoList, new infoList());
                fragmentTransaction.addToBackStack(null).commit();
                break;
            case 510: // 마이페이지 즐겨찾기 챌린지 목록
                fragmentTransaction.replace(R.id.frameMain, new infoChallengeEnrollList());
                fragmentTransaction.addToBackStack(null).commit();
            default:
                break;
        }
    }

    public void changeFragmentWasteCtgr(String ctgr) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        wasteMain newPage = new wasteMain();
        ArrayList<wasteItem> newOne = new ArrayList<wasteItem>();
        for (int i = 0; i < aCurrentData.listWaste.size(); i++) {
            if (aCurrentData.listWaste.get(i).ctgr.equals(ctgr)) {
                newOne.add(aCurrentData.listWaste.get(i));
            }
        }
        newPage.setItems(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 펀딩 리스트 불러오기
    // parameter : 스피너 값
    public void changeFragmentFundingList() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fundingList newPage = new fundingList();
        fragmentTransaction.replace(R.id.frameFundingList, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 챌린지 세부페이지 내꺼
    public void changeFragmentChallengeItemSpecific(challengeItem newOne) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeItemSpecific newPage = new challengeItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    public void changeFragmentChallengeComplete(challengeItem newOne) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeComplete newPage = new challengeComplete();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 챌린지 등록 세부페이지 내꺼
    public void changeFragmentChallengeEnrollItemSpecific(challengeItem newOne) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeEnrollItemSpecific newPage = new challengeEnrollItemSpecific();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 챌린지 세부페이지 남의 것
    public void changeFragmentChallengeItemSpecificOther(challengeItem newOne) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        challengeItemSpecificOther newPage = new challengeItemSpecificOther();
        newPage.setItem(newOne);
        fragmentTransaction.replace(R.id.frameMain, newPage);
        fragmentTransaction.addToBackStack(null).commit();
    }

    // 마이페이지
//    public void changeFragmentInfoList() {
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        infoList newPage = new infoList();
//        fragmentTransaction.replace(R.id.frameInfoList, newPage);
//        fragmentTransaction.addToBackStack(null).commit();
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
     fragmentTransaction.addToBackStack(null).commit();
     }
     */

}
