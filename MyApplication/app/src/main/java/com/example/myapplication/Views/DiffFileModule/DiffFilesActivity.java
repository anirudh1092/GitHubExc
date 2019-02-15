package com.example.myapplication.Views.DiffFileModule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.API.Models.UserCommitDiffs;
import com.example.myapplication.API.Models.UserCommitFiles;
import com.example.myapplication.API.Models.UserCommits;
import com.example.myapplication.R;
import com.example.myapplication.RootComponents.App;
import com.example.myapplication.Views.ReposModule.ReposRecyclerViewAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiffFilesActivity extends AppCompatActivity implements DiffFilesActivityMVPBase.DiffView{

    List<UserCommitFiles>diffsList;
    DiffFilesRecyclerViewAdapter adapter;
    private final String ARGS_SELECTED_COMMIT="SelectedCommit";

    @Inject
    DiffFilesActivityMVPBase.DiffPresenter presenter;

    @BindView(R.id.recycler_view_diffs)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff);

         ((App) getApplication()).getComponent().injectDiffActivity(this);

         ButterKnife.bind(this);

        diffsList=new ArrayList<>();
        adapter =new DiffFilesRecyclerViewAdapter(diffsList,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void updateData(UserCommitFiles data) {
        if(!diffsList.contains(data)){
            diffsList.add(data);
            adapter.notifyItemInserted(diffsList.size()-1);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);

        presenter.loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribeRx();
    }

    public UserCommits getSelectedRepo(){
        Gson gson = new Gson();
        UserCommits diffs = gson.fromJson(getIntent()
                .getStringExtra(ARGS_SELECTED_COMMIT), UserCommits.class);
        return diffs;
    }


}
