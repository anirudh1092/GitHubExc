package com.example.myapplication.Views.CodeDiffsModule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.API.Models.GitHubRepos;
import com.example.myapplication.API.Models.UserCommitDiffs;
import com.example.myapplication.API.Models.UserCommitFiles;
import com.example.myapplication.R;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CodeDiffsActivity extends AppCompatActivity {

    private static final String ARGS_RIGHTTEXT = "RIGHT TEXT";
    private final String ARGS_LEFTTEXT="LEFT TEXT";
    private final String TAG=getClass().getName();
    StringBuilder leftStringBuilder=new StringBuilder();
    StringBuilder rightStringBuilder= new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_diffs);
        getDiffString(getPatch());
        addFragment();
        setTitle("Diffs");
    }

    private void addFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        FragmentLeft fragmentLeft= new FragmentLeft();
        fragmentLeft.setArguments(setLeftText(leftStringBuilder.toString()));

        FragmentRight fragmentRight=new FragmentRight();
        fragmentRight.setArguments(setRightText(rightStringBuilder.toString()));

        fragmentTransaction.add(R.id.fragment_left_container,fragmentLeft);
        fragmentTransaction.add(R.id.fragment_right_container,fragmentRight);
        fragmentTransaction.commit();
   }

    public UserCommitFiles getSelectedRepo(){
        Gson gson = new Gson();
        UserCommitFiles diffs = gson.fromJson(getIntent()
                .getStringExtra("SelectedCommit"), UserCommitFiles.class);
        return diffs;
    }


    public String getDiffString(String str){
        if(str==null||str.length()==0)return "";
        String str1=str.split("@@")[1].split("\\+")[1].split(",")[0];
        int index=Integer.parseInt(str1);
        leftStringBuilder.append(str.split("@@")[1]+"\t\t\t");
        rightStringBuilder.append("\n");
        String[] strings=str.split("@@")[2].split("\\n");

        for(int i=0;i< strings.length;i++){
            if(strings[i]!=null && strings[i].length()>0){
                Log.d(TAG, "getLeftString: "+strings[i].length() +i);
                if(strings[i].charAt(0)!=' ' && strings[i].charAt(0)=='-') {
                    leftStringBuilder.append((index++) + " " + " " + strings[i]+"\n");
                    rightStringBuilder.append("\n");
                }else{
                    rightStringBuilder.append((index++)+" "+" "+ strings[i]+"\n");
                    leftStringBuilder.append("\n");
                }
            }

        }
        return leftStringBuilder.toString();
    }

    public String getPatch(){
        String temp= getSelectedRepo().getPatch();
        return temp;
    }

    public Bundle setLeftText(String str){
        Bundle bundle =new Bundle();
        bundle.putString(ARGS_LEFTTEXT,str);
        return bundle;
    }


    public Bundle setRightText(String str){
        Bundle bundle=new Bundle();
        bundle.putString(ARGS_RIGHTTEXT,str);
        return bundle;
    }
}

