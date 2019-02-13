package com.example.myapplication.API;

import com.example.myapplication.API.Models.GitHubRepos;
import com.example.myapplication.API.Models.UserCommitDiffs;
import com.example.myapplication.API.Models.UserCommits;

import java.util.List;

import dagger.Provides;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GitHubService {


    @GET("/users/{user}/repos")
    Observable<List<GitHubRepos>> getUserRepos (@Path("user") String user);

    @GET("/repos/{user}/{repoName}/commits")
    Observable<List<UserCommits>>getRepoCommits(@Path("user")String user, @Path("repoName")String repoName);


    @GET("/repos/{user}/{repoName}/commits/{sha}")
    Observable<List<UserCommitDiffs>> getDiffs(@Path("user")String user,
                                               @Path("repoName")String repoName,
                                               @Path("sha")String sha);


}
