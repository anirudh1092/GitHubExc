package com.example.myapplication.Views.DiffFileModule;

import android.util.Log;

import com.example.myapplication.API.Models.UserCommitDiffs;
import com.example.myapplication.API.Models.UserCommitFiles;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DiffFilesActivityPresenter implements DiffFilesActivityMVPBase.DiffPresenter {

    private final String TAG=getClass().getName();


    private DiffFilesActivityMVPBase.DiffModel diffModel;

    Disposable subscription=null;

    private DiffFilesActivityMVPBase.DiffView view;


    public DiffFilesActivityPresenter(DiffFilesActivityMVPBase.DiffModel diffModel) {
        this.diffModel = diffModel;
    }


    @Override
    public void loadData(String userName,String repoName,String sha) {

        diffModel.getDiffs(userName,repoName,sha)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UserCommitDiffs>() {
                    @Override
                    public void onNext(UserCommitDiffs userCommitDiffs) {


                        for(UserCommitFiles file: userCommitDiffs.getFiles()){
                            Log.d(TAG, "onNext:File Names "+file.getFilename());
                           // Log.d(TAG, "onNext: File Patch  "+file.getPatch());
                            view.updateData(file);
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
    public void setView(DiffFilesActivityMVPBase.DiffView view) {
            this.view=view;
    }
}
