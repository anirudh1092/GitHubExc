package com.example.myapplication.Views.CommitsModule;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.myapplication.API.Models.GitHubRepos;
import com.example.myapplication.API.Models.UserCommitFiles;
import com.example.myapplication.API.Models.UserCommits;
import com.example.myapplication.R;
import com.example.myapplication.RootComponents.App;
import com.example.myapplication.Views.ReposModule.ReposActivityMVPBase;
import com.example.myapplication.Views.ReposModule.ReposActivityPresenter;
import com.example.myapplication.Views.ReposModule.ReposRecyclerViewAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommitsActivity extends AppCompatActivity implements  CommitsActivityMVPBase.CommitsView{

    private final String USERNAME="UserNameString";

    List<UserCommits> commitsList;
    CommitsRecyclerVIewAdapter adapter;

    @BindView(R.id.recycler_view_commits)
    RecyclerView recyclerView;

    @Inject
    CommitsActivityMVPBase.CommitsPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits);
        commitsList=new ArrayList<>();
        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().injectCommitsActivity(this);

        adapter =new CommitsRecyclerVIewAdapter(commitsList,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void upadateData(UserCommits commit) {
        commitsList.add(commit);
        adapter.notifyItemInserted(commitsList.size()-1);
        Log.d("temp", "upadateData: "+commit.getAuthor().getLogin());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
         presenter.loadData(getUserName(),getSelectedCommit().getName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribeRx();
        commitsList.clear();
        adapter.notifyDataSetChanged();


    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribeRx();
        commitsList.clear();
        adapter.notifyDataSetChanged();
    }

    public GitHubRepos getSelectedCommit(){
        Gson gson = new Gson();
        GitHubRepos repo = gson.fromJson(getIntent()
                .getStringExtra("Selected Repo"), GitHubRepos.class);
        return repo;
    }

    public String getUserName(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString(USERNAME, "");
        return name;

    }
}
