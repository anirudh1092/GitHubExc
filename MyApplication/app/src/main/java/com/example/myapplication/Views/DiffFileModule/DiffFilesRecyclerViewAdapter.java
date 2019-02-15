package com.example.myapplication.Views.DiffFileModule;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.API.Models.UserCommitDiffs;
import com.example.myapplication.API.Models.UserCommitFiles;
import com.example.myapplication.R;
import com.example.myapplication.Views.CodeDiffsModule.CodeDiffsActivity;
import com.google.gson.Gson;

import java.util.List;

public class DiffFilesRecyclerViewAdapter extends RecyclerView.Adapter<DiffFilesRecyclerViewAdapter.ViewHolder>{

    List<UserCommitFiles>diffsList;
    Context context;

    public DiffFilesRecyclerViewAdapter(List<UserCommitFiles> diffsList, Context context) {
        this.diffsList = diffsList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View itemView=LayoutInflater.from(viewGroup.getContext())
                 .inflate(R.layout.recycler_list_item,viewGroup,false);

         ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
            String[] filePath= diffsList.get(i).getFilename().split("/");
            viewHolder.diffsFilesTxtView.setText(filePath[filePath.length-1]);
    }

    @Override
    public int getItemCount() {
        return diffsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView diffsFilesTxtView;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            diffsFilesTxtView=itemView.findViewById(R.id.textView_githubRepos);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =getLayoutPosition();
                    Gson gson = new Gson();
                    String selectedCommit = gson.toJson(diffsList.get(position));
                    Intent intent= new Intent(context, CodeDiffsActivity.class);
                    intent.putExtra("SelectedCommit",selectedCommit);
                    context.startActivity(intent);

                }
            });
        }

    }



}
