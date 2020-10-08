package com.example.temporal;


import android.app.Fragment;

import android.content.Intent;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class challengeItemSpecific extends Fragment {

    challengeItemActivity image= new challengeItemActivity();
    challengeItem item = new challengeItem();
    CalendarView calendarView;
    TextView textPointTotal;
    private long now ;

    private int GET_GALLERY_IMAGE;


    private ImageView newActivity;


    public void setItem(challengeItem newOne) {
        item.clone(newOne);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.challenge_item_specific, container,false);
        TextView textTitle = view.findViewById(R.id.textTitle);
        TextView textDescription = view.findViewById(R.id.textDescription);
        textTitle.setText(item.title);
        textDescription.setText(item.desc);
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
            newActivity = new ImageView(this.getContext());
            LinearLayout slotActivity = new LinearLayout(this.getContext());
            slotActivity.setOrientation(LinearLayout.HORIZONTAL);
            GET_GALLERY_IMAGE = i;
            // 아래 함수에 사진 찍는거 연결하면 됨
            // 연결하고 나서 프레그먼트바 계산 해줘야되니까 양승혁한테 말하고
            newActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, GET_GALLERY_IMAGE);
                    // 시간
                    now = System.currentTimeMillis();
                    Date date = new Date(now);
                    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    String time = mFormat.format(date);
                    image.reg_date=time;
                }
            });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            newActivity.setImageURI(selectedImageUri);
            image.url=selectedImageUri.toString();
        }
    }
}
