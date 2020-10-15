package com.example.temporal;

public class userActivity {
    // type: 챌린지 -> 0, 펀딩 엑티비티 -> 1, 챌린지 엑티비티 -> 2, 펀딩 -> 3
    int type;
    String date;
    String title;
    int point;

    public void setFromChallengeItem(challengeItem ori) {
        type = 0;
        date = ori.regdate;
        title = ori.title;
        point = ori.chalPoint;
    }

    public void setFromFundingActivity(fundingItemActivityForGet ori) {
        type = 1;
        date = ori.funddate;
        point = ori.point;
        for (fundingItem item : aCurrentData.listFunding) {
            if (item.fund_id == ori.fund_id) {
                title = item.fund_inst;
            }
        }
    }

    public void setFromChallengeActivity(challengeItemActivityForGet ori) {
        type = 2;
        date = ori.regdate;
        point = 0;
        for (challengeItem item : aCurrentData.listMyChallenge) {
            if (item.chalId == ori.chalId) {
                title = item.title;
            }
        }
    }

    public void setFromFunding(fundingItem ori) {
        type = 3;
        //date = ori.finDate;
        title = ori.fund_inst;
        point = 0;
    }

}
