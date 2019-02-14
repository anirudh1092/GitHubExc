package com.example.myapplication.Views.DiffFileModule;

import android.util.Log;

import com.example.myapplication.API.Models.UserCommitDiffs;
import com.example.myapplication.API.Models.UserCommitFiles;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DiffActivityPresenter implements DiffActivityMVPBase.DiffPresenter {

    private final String TAG=getClass().getName();


    private DiffActivityMVPBase.DiffModel diffModel;

    Disposable subscription=null;

    private DiffActivityMVPBase.DiffView view;


    public DiffActivityPresenter(DiffActivityMVPBase.DiffModel diffModel) {
        this.diffModel = diffModel;
    }


    @Override
    public void loadData() {
        String username="PhilJay";
        String repoName="MPAndroidChart";
        String sha="aea2ff3417e30d6d4b1ce7e777cbd8bc83e1c95d";

        diffModel.getDiffs(username,repoName,sha)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UserCommitDiffs>() {
                    @Override
                    public void onNext(UserCommitDiffs userCommitDiffs) {
                        for(UserCommitFiles file: userCommitDiffs.getFiles()){
                            Log.d(TAG, "onNext:File Names "+file.getFilename());
                            Log.d(TAG, "onNext: Fila Patch  "+file.getPatch());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());;
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete:Completed ");
                    }
                });

    }



    @Override
    public void unsubscribeRx() {
        //if(view!=null)
        //    subscription.dispose();
    }

    @Override
    public void setView(DiffActivityMVPBase.DiffView view) {
            this.view=view;
    }
}
