package com.example.dldmd.globalsns.pid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dldmd.globalsns.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class PidDetail extends AppCompatActivity {

    String id;              //session에 담은 id 값
    String contentsId;      //글 고유 id
    String contentsInfo;    //글 id를 통해 데이터를 꺼내옴
    JSONArray jsonArray;
    StringBuffer sb;

    //데이터를 담을 텍스트뷰
    TextView userNameTv;
    TextView userIdTv;
    TextView nowTimeTv;
    TextView contentsTv;

    String userId;
    String userName;
    String contents;
    String nowTime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piddetail);

        SharedPreferences sessionSf = getSharedPreferences("USER_SESSION",MODE_PRIVATE);
        id = sessionSf.getString("SESSION_ID","");

        Intent gIntent = getIntent();
        contentsId = gIntent.getStringExtra("CONTENTS_INFO");


        SharedPreferences contentsSf = getSharedPreferences("CONTENTS",MODE_PRIVATE);
        contentsInfo = contentsSf.getString(contentsId,"");
        System.out.println(contentsId+"//"+contentsInfo);
        //텍스트뷰 생성
        userNameTv = (TextView)findViewById(R.id.userName);
        userIdTv = (TextView)findViewById(R.id.userId);
        nowTimeTv = (TextView)findViewById(R.id.nowTime);
        contentsTv = (TextView)findViewById(R.id.contents);

        //json type -> contentsInfo 찢어서 저장
        sb = new StringBuffer();
        try{
            jsonArray = new JSONArray(contentsInfo);
            System.out.println(contentsInfo);
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                userId = jsonObject.getString("userId");
                userName = jsonObject.getString("userName");
                contents = jsonObject.getString("contents");
                nowTime = jsonObject.getString("nowTime");
            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"에러가 발생하여 이전화면으로 돌아갑니다",Toast.LENGTH_SHORT).show();
            onBackPressed();
        }


        userNameTv.setText(userName);
        userIdTv.setText(userId);
        nowTimeTv.setText(nowTime);
        contentsTv.setText(contents);

    }

}
