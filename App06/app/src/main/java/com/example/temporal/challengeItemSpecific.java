package com.example.temporal;


import android.app.Fragment;

import android.content.Intent;

import android.graphics.Color;
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

import static android.app.Activity.RESULT_OK;

public class challengeItemSpecific extends Fragment {

    challengeItem item = new challengeItem();
    CalendarView calendarView;
    TextView textPointTotal;

    private final int GET_GALLERY_IMAGE1=1;
    private final int GET_GALLERY_IMAGE2=2;
    private final int GET_GALLERY_IMAGE3=3;
    private final int GET_GALLERY_IMAGE4=4;
    private final int GET_GALLERY_IMAGE5=5;
    private final int GET_GALLERY_IMAGE6=6;
    private final int GET_GALLERY_IMAGE7=7;
    private final int GET_GALLERY_IMAGE8=8;
    private final int GET_GALLERY_IMAGE9=9;


    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;
    private ImageView image7;
    private ImageView image8;
    private ImageView image9;

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
            ImageView newActivity = new ImageView(this.getContext());
            LinearLayout slotActivity = new LinearLayout(this.getContext());
            slotActivity.setOrientation(LinearLayout.HORIZONTAL);
            final int GET_GALLERY_IMAGE = i;
            // 아래 함수에 사진 찍는거 연결하면 됨
            // 연결하고 나서 프레그먼트바 계산 해줘야되니까 양승혁한테 말하고
            newActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, GET_GALLERY_IMAGE);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        /*
        if (requestCode == GET_GALLERY_IMAGE1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            image1.setImageURI(selectedImageUri);
        }

        else if (requestCode == GET_GALLERY_IMAGE2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            image2.setImageURI(selectedImageUri);
        }
        else if (requestCode == GET_GALLERY_IMAGE3 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            image3.setImageURI(selectedImageUri);
        }
        else if (requestCode == GET_GALLERY_IMAGE4 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            image4.setImageURI(selectedImageUri);
        }
        else if (requestCode == GET_GALLERY_IMAGE5 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            image5.setImageURI(selectedImageUri);
        }
        else if (requestCode == GET_GALLERY_IMAGE6 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            image6.setImageURI(selectedImageUri);
        }
        else if (requestCode == GET_GALLERY_IMAGE7 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            image7.setImageURI(selectedImageUri);
        }
        else if (requestCode == GET_GALLERY_IMAGE8 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            image8.setImageURI(selectedImageUri);
        }
        else if (requestCode == GET_GALLERY_IMAGE9 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            image9.setImageURI(selectedImageUri);
        }

         */
    }

}
