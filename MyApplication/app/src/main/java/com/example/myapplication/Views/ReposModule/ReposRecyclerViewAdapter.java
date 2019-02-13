package com.example.myapplication.Views.ReposModule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.API.Models.GitHubRepos;
import com.example.myapplication.R;

import java.util.List;

import butterknife.BindView;

public class ReposRecyclerViewAdapter extends RecyclerView.Adapter<ReposRecyclerViewAdapter.ViewHolder> {

    public final String TAG="ReposRecyclerViewAdapter";

    List<GitHubRepos> dataList;
    Context context;

    public ReposRecyclerViewAdapter(Context context, List<GitHubRepos> dataList) {
        this.dataList = dataList;
        this.context=context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.recycler_list_item,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String name= dataList.get(i).getName();
        viewHolder.textView.setText(name);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textView_githubRepos)
        TextView textView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView_githubRepos);
            //  Activity activity=(Activity)context;
            Log.d("temp", "ViewHolder: "+context);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getLayoutPosition();
//                    Intent intent= new Intent(context, CommitsActivity.class);
//                    intent.putExtra("SelectedRepo",dataList.get(position));
//                    context.startActivity(intent);
                }
            });
        }
    }

}
