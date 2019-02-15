package com.example.myapplication.Views.CodeDiffsModule;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentRight extends Fragment {
    private static final String ARGS_RIGHTTEXT = "RIGHT TEXT";

    @BindView(R.id.textView_right)
    TextView textView_right;

    public FragmentRight() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_right,container,false);
        ButterKnife.bind(this,view);
        if(getArguments()!=null){
            String data=getArguments().getString(ARGS_RIGHTTEXT);

            SpannableString spannableString = new SpannableString(data+"\n");
            BackgroundColorSpan backgroundSpan = new BackgroundColorSpan(Color.GREEN);
            spannableString.setSpan(backgroundSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView_right.setText(spannableString);
        }


        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
