package com.example.myapplication.Views.ReposModule;


import android.util.Log;

import com.example.myapplication.API.Models.GitHubRepos;
import com.example.myapplication.Views.CommitsModule.CommitsActivityMVPBase;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ReposActivityPresenter implements  ReposActivityMVPBase.Presenter{

    private final String TAG=getClass().getName();
    ReposActivityMVPBase.Model model;
    private Disposable subscription=null;
    private ReposActivityMVPBase.View view;;
    public ReposActivityPresenter(ReposActivityMVPBase.Model model) {
        this.model = model;
    }


    @Override
    public void loadData(String userName) {
       view.showProgressBar();
       subscription= model.getRepos(userName)
                .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribeWith(new DisposableObserver<List<GitHubRepos>>() {
                     @Override
                     public void onNext(List<GitHubRepos> gitHubRepos) {
                         for (int i = 0; i < gitHubRepos.size(); i++) {
                             view.updateData(gitHubRepos.get(i));
                         }
                     }
                     @Override
                     public void onError(Throwable e) {
                         Log.d(TAG, "onError: "+e.getMessage());
                         view.hideProgressBar();
                         view.showToastMessage(e.getMessage());
                     }

                     @Override
                     public void onComplete() {
                         Log.d(TAG, "onComplete: ");
                         view.hideProgressBar();
                     }
                 });
    }

    @Override
    public void unsubscribeRX() {
        subscription.dispose();
    }



    @Override
    public void setView(ReposActivityMVPBase.View view) {
            this.view=view;
    }
}
