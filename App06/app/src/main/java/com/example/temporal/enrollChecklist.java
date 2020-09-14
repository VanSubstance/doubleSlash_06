package com.example.temporal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class enrollChecklist extends Fragment {
    enrollChecklistItem item;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enroll_checklist, container, false);
        item = new enrollChecklistItem();
        item.init(getArguments().getString("ctgr"), getArguments().getStringArrayList("seq"));
        LinearLayout layoutChecklist = view.findViewById(R.id.layoutChecklist);
        for (String seq : item.seq) {
            LinearLayout newOne = new LinearLayout(view.getContext());
            TextView text = new TextView(view.getContext());
            text.setText(seq);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 9;
            text.setLayoutParams(params);
            Switch swit = new Switch(view.getContext());
            params.weight = 1;
            swit.setLayoutParams(params);
            newOne.addView(text);
            newOne.addView(swit);
            layoutChecklist.addView(newOne);
        }
        return view;
    }

}
