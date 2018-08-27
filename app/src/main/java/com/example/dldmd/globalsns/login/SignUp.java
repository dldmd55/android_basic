package com.example.dldmd.globalsns.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dldmd.globalsns.R;


public class SignUp extends AppCompatActivity {
    String checkN = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_lay);


        Button signUp = (Button)findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userId = (EditText)findViewById(R.id.userId);
                EditText userPwd = (EditText)findViewById(R.id.userPwd);
                EditText userPwdCh = (EditText)findViewById(R.id.userPwdCh);
                EditText userName = (EditText)findViewById(R.id.userName);
                RadioGroup lanG=(RadioGroup)findViewById(R.id.lanG);
                RadioButton ko =(RadioButton)findViewById(R.id.rko);
                RadioButton en =(RadioButton)findViewById(R.id.ren);
                RadioButton ja =(RadioButton)findViewById(R.id.rja);

                lanG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkNation) {

                        if(checkNation == R.id.rko){
                            checkN = "ko";
                            Toast.makeText(getApplicationContext(),"한국어",Toast.LENGTH_SHORT).show();
                        }else if(checkNation == R.id.ren){
                            checkN = "en";
                            Toast.makeText(getApplicationContext(),"영어",Toast.LENGTH_SHORT).show();
                        }else{
                            checkN = "ja";
                            Toast.makeText(getApplicationContext(),"일본어",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                if(userId.getText().toString().equals(null)||userPwd.getText().toString()==null||userPwdCh.getText().toString()==null||userName.getText().toString()==null){
                    Toast.makeText(getApplicationContext(),"빈칸이 존재 합니다.",Toast.LENGTH_SHORT).show();
                }else{
                    if(!userPwd.getText().toString().equals(userPwdCh.getText().toString())){
                        Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                    }else{
                        UserVO userVO = new UserVO();
                        userVO.setUserId(userId.getText().toString());
                        userVO.setUserPwd(userPwd.getText().toString());
                        userVO.setUserName(userName.getText().toString());
                        userVO.setUserNation(checkN);

                        setUser(userVO,"USER_KEY");

                        Intent intent = new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    //SharedPreferences에 유저 정보 저장
    public void setUser(UserVO userVO,String key){

        StringBuffer stringBuffer;
        String userInfo=
                "[{'userId':'"+userVO.getUserId()+"','userPwd':"+userVO.getUserPwd()+",'userName':'"+userVO.getUserName()+"','userNation':'"+userVO.getUserNation()+"'}]";
        SharedPreferences sf = getSharedPreferences(key,MODE_PRIVATE);

        SharedPreferences.Editor editor = sf.edit();
        editor.putString(userVO.userId,userInfo);
        editor.commit();
        System.out.println("저장완료");
    }
    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }
}
