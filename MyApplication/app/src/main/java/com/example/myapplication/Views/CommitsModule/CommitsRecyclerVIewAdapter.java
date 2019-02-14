package com.example.myapplication.Views.CommitsModule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.API.Models.UserCommitDiffs;
import com.example.myapplication.API.Models.UserCommits;
import com.example.myapplication.R;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CommitsRecyclerVIewAdapter extends RecyclerView.Adapter<CommitsRecyclerVIewAdapter.ViewHolder> {

    List<UserCommits> userCommits;
    Context context;

    public CommitsRecyclerVIewAdapter(List<UserCommits> userCommits, Context context) {
        this.userCommits = userCommits;
        this.context = context;
        Log.d(TAG, "CommitsRecyclerVIewAdapter: "+userCommits);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_list_item, viewGroup,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String commitName=userCommits.get(i).getSha();
        viewHolder.userCommitsTextView.setText(commitName);
    }

    @Override
    public int getItemCount() {
        return userCommits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView userCommitsTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userCommitsTextView=itemView.findViewById(R.id.textView_githubRepos);
        }
    }


}
