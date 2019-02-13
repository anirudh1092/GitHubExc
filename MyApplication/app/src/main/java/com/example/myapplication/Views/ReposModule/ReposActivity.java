package com.example.myapplication.Views.ReposModule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.API.Models.GitHubRepos;
import com.example.myapplication.R;
import com.example.myapplication.RootComponents.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposActivity extends AppCompatActivity implements  ReposActivityMVPBase.View {


    List<GitHubRepos> reposList;

    @BindView(R.id.reposRecyclerView)
    RecyclerView recyclerView;

    ReposRecyclerViewAdapter adapter;

    @Inject
    ReposActivityMVPBase.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        reposList=new ArrayList<>();

        List<String>repos=new ArrayList<>();

        repos.add("repos1");

        adapter =new ReposRecyclerViewAdapter(getApplicationContext(),reposList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);


    }

    @Override
    public void updateData(GitHubRepos data) {

        reposList.add(data);
        adapter.notifyItemInserted(reposList.size() - 1);
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
        presenter.unsubscribeRX();
        reposList.clear();
        adapter.notifyDataSetChanged();


    }

}
