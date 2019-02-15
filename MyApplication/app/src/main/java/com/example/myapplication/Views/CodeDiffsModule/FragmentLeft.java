package com.example.myapplication.Views.CodeDiffsModule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.xeoh.android.texthighlighter.TextHighlighter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentLeft extends Fragment {

    private final String ARGS_LEFTTEXT="LEFT TEXT";
    private final String TAG=getClass().getName();

    @BindView(R.id.textView_left)
    TextView textView_left;

    public FragmentLeft() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_left,container,false);
        ButterKnife.bind(this,view);
        if(getArguments()!=null){
            String data=getArguments().getString(ARGS_LEFTTEXT);

            SpannableString spannableString = new SpannableString(data+"\n");
            BackgroundColorSpan backgroundSpan = new BackgroundColorSpan(ContextCompat.getColor(getContext(),R.color.red_light));
            spannableString.setSpan(backgroundSpan, 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView_left.setText(spannableString);
        }

        return view;
    }
}
