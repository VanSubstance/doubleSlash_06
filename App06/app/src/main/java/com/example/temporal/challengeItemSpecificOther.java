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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import static android.app.Activity.RESULT_OK;

public class challengeItemSpecificOther extends Fragment {
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

        TableLayout layoutImages = view.findViewById(R.id.layoutImages);
        TableRow.LayoutParams settingRow = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams settingImage;
        settingRow.setMargins(0, 20, 0 ,0);
        settingRow.gravity = Gravity.CENTER | Gravity.LEFT;
        TableRow rowImages = null;
        // 이미지 동적 생성
        for (int i = 0; i < item.acvts.size(); i++) {
            ImageView newActivity = new ImageView(this.getContext());
            final int GET_GALLERY_IMAGE = i;
            newActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent. setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, GET_GALLERY_IMAGE);
                }
            });
            newActivity.setScaleX(110);
            newActivity.setScaleY(110);
            settingImage = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            switch (i % 3) {
                case 0: // Row 생성 + 사진 생성: 좌 20
                    rowImages = new TableRow(this.getContext());
                    rowImages.setLayoutParams(settingRow);
                    settingImage.setMargins(20, 0, 0, 0);
                    newActivity.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    newActivity.setLayoutParams(settingImage);
                    rowImages.addView(newActivity);
                    break;
                case 1: // 사진 생성: 좌 20 우 20
                    settingImage.setMargins(20, 0, 20, 0);
                    newActivity.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    newActivity.setLayoutParams(settingImage);
                    rowImages.addView(newActivity);
                    break;
                case 2: // 사진 생성: 우 20
                    settingImage.setMargins(0, 0, 20, 0);
                    newActivity.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    newActivity.setLayoutParams(settingImage);
                    rowImages.addView(newActivity);
                    layoutImages.addView(rowImages);
                    break;
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