package com.example.temporal;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class fundingList extends Fragment implements OnItemClickForFunding {
    RecyclerView viewList;
    fundingItemAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.funding_list, container,false);

//        buttonFunding.setOnClickListener(new Button.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("펀딩이 완료되었습니다!");
//                builder.setMessage("펀딩 완료 메시지");
//                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        //토스트 메시지
//                        Toast.makeText(getActivity().getApplicationContext(), "펀딩 완료", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                //builder.setNeutralButton("취소", null);
//                builder.show();
//            }
//        });

        viewList = view.findViewById(R.id.recyclerView);
        viewList.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // 부드럽게 넘기기
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(viewList);

        adapter = new fundingItemAdapter(aCurrentData.listFunding, this);
        viewList.setAdapter(adapter);

//        textSpecificTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.funding_translate_top);   // 에니메이션 설정 파일
//                viewList.startAnimation(anim);
//            }
//        });

        return view;

    }

    @Override
        public void onClick(fundingItem newOne) {
        TextView textTitle = viewList.findViewById(R.id.textTitle);
        TextView textDescription = viewList.findViewById(R.id.textDescription);
         TextView textSpecificTitle = viewList.findViewById(R.id.textSpecificTitle);
         TextView textSpecificDescription = viewList.findViewById(R.id.textSpecificDescription);
         Button fundingbutton = viewList.findViewById(R.id.funding_button);
        TextView textTitleClick = viewList.findViewById(R.id.textTitleClick);
        TextView textDescriptionClick = viewList.findViewById(R.id.textDescriptionClick);

        textTitleClick.setVisibility(View.VISIBLE);
        textDescriptionClick.setVisibility(View.VISIBLE);
        textSpecificTitle.setVisibility(View.VISIBLE);
        textSpecificDescription.setVisibility(View.VISIBLE);
        fundingbutton.setVisibility(View.VISIBLE);
        textTitle.setVisibility(View.GONE);
        textDescription.setVisibility(View.GONE);


        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.funding_translate_top);   // 에니메이션 설정 파일
        viewList.startAnimation(anim);


    }

    @Override
    public void onScroll(fundingItem newOne) {
        TextView textTitle = viewList.findViewById(R.id.textTitle);
        TextView textDescription = viewList.findViewById(R.id.textDescription);
        TextView textSpecificTitle = viewList.findViewById(R.id.textSpecificTitle);
        TextView textSpecificDescription = viewList.findViewById(R.id.textSpecificDescription);
        Button fundingbutton = viewList.findViewById(R.id.funding_button);
        TextView textTitleClick = viewList.findViewById(R.id.textTitleClick);
        TextView textDescriptionClick = viewList.findViewById(R.id.textDescriptionClick);

        textTitleClick.setVisibility(View.VISIBLE);
        textDescriptionClick.setVisibility(View.VISIBLE);
        textSpecificTitle.setVisibility(View.VISIBLE);
        textSpecificDescription.setVisibility(View.VISIBLE);
        fundingbutton.setVisibility(View.VISIBLE);
        textTitle.setVisibility(View.GONE);
        textDescription.setVisibility(View.GONE);


        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.funding_translate_top);   // 에니메이션 설정 파일
        viewList.startAnimation(anim);
    }

    //
////        textSpecificTitle.(View.VISIBLE);
//
//
////          Intent intent = new Intent(getActivity(), fundingList.class);
////          intent.putExtra("extra_data", "from subactivity");
////          getActivity().setResult(Activity.RESULT_OK, intent);
////          getActivity().overridePendingTransition(R.anim.funding_translate_top, R.anim.stay);
////          getActivity().finish();
//
////        Intent intent = new Intent(getActivity(), fundingList.class);
////        Intent intent = new Intent();
////        getActivity().startActivity(intent);
////        (getActivity()).overridePendingTransition(R.anim.funding_translate_top, R.anim.stay);
////
////        ((MainActivity)getActivity()).overridePendingTransition(0, 0);
////        getActivity().finish();
////        ((MainActivity)getActivity()).overridePendingTransition(R.anim.stay,R.anim.funding_translate_bottom);
//
    @Override
    public void onFundingClick(fundingItem newOne) {

    }

    @Override
    public void onLeftCLick(fundingItem newOne) {

    }

    @Override
    public void onRightCLick(fundingItem newOne) {

    }

}

