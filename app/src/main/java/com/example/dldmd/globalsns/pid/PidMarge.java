package com.example.dldmd.globalsns.pid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dldmd.globalsns.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PidMarge extends AppCompatActivity {

    Button saveBtn; //저장

    TextView userName;
    EditText editContents;
    String id;
    Date today = new Date();
    String nowTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pidmarge);

        //세션ID 찾기
        SharedPreferences sessionSf = getSharedPreferences("USER_SESSION",MODE_PRIVATE);
        id = sessionSf.getString("SESSION_ID","");
        //test
        id = "a323";

        userName = (TextView) findViewById(R.id.userName);
        editContents = (EditText)findViewById(R.id.editContents);

        saveBtn = (Button)findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat time = new SimpleDateFormat("yyyy/MM/dd hh:mm:dd");
                nowTime = time.format(today);

                //contentsid 난수 생성
                String uuid = UUID.randomUUID().toString();
                PidVO pidVO = new PidVO(id,userName.getText().toString(),editContents.getText().toString(),nowTime,uuid);

                //json타입으로 글저장
                String jsonContents = pidVO.pidVOJson();

                //저장
                SharedPreferences contentSf = getSharedPreferences("CONTENTS",MODE_PRIVATE);
                SharedPreferences.Editor editor = contentSf.edit();
                editor.putString(editContents.getText().toString(),jsonContents);
                editor.commit();
                System.out.println("저장완료");
                Intent intent = new Intent(getApplicationContext(),PidDetail.class);
                intent.putExtra("CONTENTS_INFO",editContents.getText().toString());
                startActivity(intent);
            }
});
        }


}
