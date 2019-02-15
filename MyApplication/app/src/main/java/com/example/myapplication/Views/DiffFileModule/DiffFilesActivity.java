package com.example.myapplication.Views.DiffFileModule;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.API.Models.GitHubRepos;
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

    private final String USERNAME="UserNameString";
    private static final String SELECTEDREPO ="SelectedREPO";
    private String userName="";
    private String repoName="";
    private String sha="";
    List<String>tempList=new ArrayList<>();

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

        setProperties();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public void updateData(UserCommitFiles data) {
        if(!tempList.contains(data.getFilename())){
            if(data!=null){
                diffsList.add(data);
                tempList.add(data.getFilename());
                adapter.notifyItemInserted(diffsList.size()-1);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        if(userName!=null && repoName!=null && sha!=null){
            presenter.loadData(userName,repoName,sha);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public UserCommits getSelectedCommit(){
        Gson gson = new Gson();
        UserCommits diffs = gson.fromJson(getIntent()
                .getStringExtra(ARGS_SELECTED_COMMIT), UserCommits.class);
        return diffs;
    }

    public String getUserName(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString(USERNAME, "");
        return name;
    }
    public String getSelectedRepo(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String repoName = preferences.getString(SELECTEDREPO, "");
        return repoName;
    }

    public void setProperties(){
        userName=getUserName();
        repoName=getSelectedRepo();
        sha=getSelectedCommit().getSha();
    }
}
