package com.example.myapplication.Views.ReposModule;

import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.API.Models.GitHubRepos;
import com.example.myapplication.R;
import com.example.myapplication.RootComponents.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposActivity extends AppCompatActivity implements  ReposActivityMVPBase.View {

    private final String USERNAME="UserNameString";

    List<GitHubRepos> reposList;

    @BindView(R.id.progress_circular)
    ProgressBar progressBar;

    @BindView(R.id.reposRecyclerView)
    RecyclerView recyclerView;

    ReposRecyclerViewAdapter adapter;

    @BindView(R.id.editText_getUserName)
    EditText userNameEditText;

    @Inject
    ReposActivityMVPBase.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        reposList=new ArrayList<>();
        progressBar.setVisibility(View.GONE);

        adapter =new ReposRecyclerViewAdapter(getApplicationContext(),reposList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);

        setTitle("Repositories");


        userNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    reposList.clear();
                    hideSoftKeyBoard();
                    presenter.loadData(userNameEditText.getText().toString());
                    saveUserName(userNameEditText.getText().toString());

                }
                return false;
            }
        });
    }

    @Override
    public void updateData(GitHubRepos data) {
        Log.d("temp", "updateData:Update Repos Data "+data.getName());
        reposList.add(data);
        adapter.notifyItemInserted(reposList.size() - 1);
    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        String userName=userNameEditText.getText().toString();
        if(userName!=null)
        presenter.loadData(userName);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribeRX();
        reposList.clear();
        adapter.notifyDataSetChanged();
    }

    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }


    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if(imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(USERNAME,userNameEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            String message = savedInstanceState.getString(USERNAME);
            userNameEditText.setText(message);
        }
    }


    public void saveUserName(String name){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME,name);
        editor.apply();
    }
    public void showToastMessage(String error){
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
    }
}
