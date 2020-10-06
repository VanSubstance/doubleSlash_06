package com.example.temporal;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;


public class challengeItemSpecificOther extends Fragment {
    challengeItem item = new challengeItem();
    CalendarView calendarView;
    TextView textPointTotal;

    public void setItem(challengeItem newOne) {
        item.clone(newOne);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_item_specific, container,false);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textDescription = view.findViewById(R.id.textDescription);
        textTitle.setText("남의 것: " + item.title);
        textDescription.setText("남의 것: " + item.desc);
        textPointTotal = view.findViewById(R.id.textPointTotal);
        textPointTotal.setText(String.valueOf(item.point));

        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setSelectionType(SelectionType.NONE);
        calendarView.setWeekendDayTextColor(Color.RED);
        int colorChosen = Color.parseColor("#0096c6");
        calendarView.getConnectedDaysManager().setConnectedDaysList(null);
        calendarView.addConnectedDays(new ConnectedDays(item.days, colorChosen, Color.YELLOW, Color.WHITE));
        calendarView.getConnectedDaysManager().setConnectedDaysList(null);

        LinearLayout layoutImages = view.findViewById(R.id.layoutImages);
        LinearLayout rowImages = null;
        LinearLayout.LayoutParams settingRow = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        settingRow.setMargins(0, 40, 0 ,0);


        // 이미지 동적 생성
        for (int i = 0; i < item.acvts.size(); i++) {
            if ( i % 3 == 0) {
                rowImages = new LinearLayout(this.getContext());
                rowImages.setOrientation(LinearLayout.HORIZONTAL);
                rowImages.setLayoutParams(settingRow);
            }
            ImageView newActivity = new ImageView(this.getContext());
            LinearLayout slotActivity = new LinearLayout(this.getContext());
            slotActivity.setOrientation(LinearLayout.HORIZONTAL);
            newActivity.setImageResource(R.drawable.border_square_black_edge_1dp);
            LinearLayout.LayoutParams settingImage = new LinearLayout.LayoutParams(400, 400);
            settingImage.setMargins(20, 0, 20, 0);
            newActivity.setLayoutParams(settingImage);
            newActivity.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams settingSlot = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            settingSlot.weight = 1;
            settingSlot.gravity = Gravity.CENTER;
            slotActivity.setLayoutParams(settingSlot);
            slotActivity.addView(newActivity);
            rowImages.addView(slotActivity);
            if (i % 3 == 2 || i + 1 == item.acvts.size()) {
                layoutImages.addView(rowImages);
            }
        }

        return view;
    }



}