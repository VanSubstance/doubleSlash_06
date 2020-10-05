package com.example.temporal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;

import static android.app.Activity.RESULT_OK;

public class challengeItemSpecificOther extends Fragment {
    challengeItem item = new challengeItem();

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
        CalendarView calendarView = view.findViewById(R.id.calendarView);
        calendarView.setSelectionType(SelectionType.NONE);
        calendarView.setWeekendDayTextColor(Color.RED);

        image1 = (ImageView)view.findViewById(R.id.image1);
        image2 = (ImageView)view.findViewById(R.id.image2);
        image3 = (ImageView)view.findViewById(R.id.image3);
        image4 = (ImageView)view.findViewById(R.id.image4);
        image5 = (ImageView)view.findViewById(R.id.image5);
        image6 = (ImageView)view.findViewById(R.id.image6);
        image7 = (ImageView)view.findViewById(R.id.image7);
        image8 = (ImageView)view.findViewById(R.id.image8);
        image9 = (ImageView)view.findViewById(R.id.image9);


        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                switch (view.getId()) {
                    case R.id.image1:
                        startActivityForResult(intent, GET_GALLERY_IMAGE1);
                        break;
                    case R.id.image2:
                        startActivityForResult(intent, GET_GALLERY_IMAGE2);
                        break;
                    case R.id.image3:
                        startActivityForResult(intent, GET_GALLERY_IMAGE3);
                        break;
                    case R.id.image4:
                        startActivityForResult(intent, GET_GALLERY_IMAGE4);
                        break;
                    case R.id.image5:
                        startActivityForResult(intent, GET_GALLERY_IMAGE5);
                        break;
                    case R.id.image6:
                        startActivityForResult(intent, GET_GALLERY_IMAGE6);
                        break;
                    case R.id.image7:
                        startActivityForResult(intent, GET_GALLERY_IMAGE7);
                        break;
                    case R.id.image8:
                        startActivityForResult(intent, GET_GALLERY_IMAGE8);
                        break;
                    case R.id.image9:
                        startActivityForResult(intent, GET_GALLERY_IMAGE9);
                        break;
                }
            }
        };

        image1.setOnClickListener(btnListener);
        image2.setOnClickListener(btnListener);
        image3.setOnClickListener(btnListener);
        image4.setOnClickListener(btnListener);
        image5.setOnClickListener(btnListener);
        image6.setOnClickListener(btnListener);
        image7.setOnClickListener(btnListener);
        image8.setOnClickListener(btnListener);
        image9.setOnClickListener(btnListener);

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
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
    }



}