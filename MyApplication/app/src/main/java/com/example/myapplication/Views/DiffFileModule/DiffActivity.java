package com.example.myapplication.Views.DiffFileModule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.API.Models.UserCommitDiffs;
import com.example.myapplication.R;
import com.example.myapplication.RootComponents.App;

import javax.inject.Inject;

public class DiffActivity extends AppCompatActivity implements DiffActivityMVPBase.DiffView{


    @Inject
    DiffActivityMVPBase.DiffPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff);

         ((App) getApplication()).getComponent().injectDiffActivity(this);
    }

    @Override
    public void updateData(UserCommitDiffs data) {

    }



    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribeRx();
    }
}
