package com.example.dldmd.globalsns.pid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.dldmd.globalsns.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PidList extends AppCompatActivity {
    Toolbar menuBar;

    ArrayList<String> keyList = new ArrayList<String>();
    List resultList = new ArrayList();

    //리사이클러뷰 연동
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    PidAdapter pidAdapter;
    PidVO pidVO;

    //추출된 key값 다시 찢기
    JSONArray jsonArray;
    StringBuffer sb;

    //set할 데이터
    String contentsInfo;
    String userId;
    String userName;
    String contents;
    String nowTime;
    String contentsId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pidlist);

        menuBar = (Toolbar)findViewById(R.id.menuBar);
        setSupportActionBar(menuBar);



        //리사이클뷰 xml 연동
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //리니어 레이아웃 객체 생성
        linearLayoutManager = new LinearLayoutManager(this);
        //리니어 레이아웃 리사이클러뷰에 세로로 붙일수 있게 지정
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //contentsSf값 꺼내오기
        SharedPreferences contentsSf = getSharedPreferences("CONTENTS",MODE_PRIVATE);
        Iterator<String> contentsList = contentsSf.getAll().keySet().iterator();
        //key(contentsId) 추출
        while (contentsList.hasNext()){
            String key = contentsList.next();
            keyList.add(key);
        }
        ArrayList<PidVO> pidContent = new ArrayList<>();
        for(int index = 0;index<keyList.size();index++) {
            resultList.add(contentsSf.getString(keyList.get(index), ""));
        }

        for(int index = 0;index<resultList.size();index++) {
            sb = new StringBuffer();
            try {
                System.out.println(resultList.get(index).toString());
                jsonArray = new JSONArray(resultList.get(index).toString());
                System.out.println("여까진 되라..제발");
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("에러");
                Toast.makeText(getApplicationContext(), "에러가 발생하여 이전화면으로 돌아갑니다", Toast.LENGTH_SHORT).show();
            }
                }
            try{
                for(int i=0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    userId = jsonObject.getString("userId");
                    userName = jsonObject.getString("userName");
                    contents = jsonObject.getString("contents");
                    nowTime = jsonObject.getString("nowTime");
                    contentsId = jsonObject.getString("contentsId");
                    pidContent.add(new PidVO(userId, userName, contents, nowTime, contentsId));
                }
            }catch (Exception e){

            }



        pidContent.add(new PidVO("test1","테스트1","테스트1의 내용을 담겠습니다.","작성시간","123"));
        pidContent.add(new PidVO("test2","테스트2","테스트2의 내용을 담겠습니다.","작성시간","234"));
        pidContent.add(new PidVO("test3","테스트3","테스트3의 내용을 담겠습니다.","작성시간","122"));
        pidContent.add(new PidVO("test4","테스트4","테스트4의 내용을 담겠습니다.","작성시간","113"));

        //리사이클러뷰를 생성해놓은 리니어 레이아웃으로 채운다
        recyclerView.setLayoutManager(linearLayoutManager);

        //애니메이션 디폴트
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //adapter 생성
        pidAdapter = new PidAdapter(pidContent);
        recyclerView.setAdapter(pidAdapter);

        /*//툴바 왼쪽에 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.listbtn);*/


    }

    //toolbar에 메뉴 레이아웃 넣어주기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    //toolbar레이아웃 셀렉트 이벤트 발생
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        Toast.makeText(getApplicationContext(),item.getTitle().toString(),Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }


}
