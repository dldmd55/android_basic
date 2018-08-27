package com.example.dldmd.globalsns.login;

import android.support.v7.app.AppCompatActivity;

public class UserVO extends AppCompatActivity{
    String userId;
    String userPwd;
    String userName;
    String userNation;
    //회원정보 sf key
    String USER_KEY="USER_KEY";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNation() {
        return userNation;
    }

    public void setUserNation(String userNation) {
        this.userNation = userNation;
    }



    //회원가입시 생성되는 객체
    public void UserVO(String userId,String userPwd,String userName,String userNation){
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userNation = userNation;
    }



}
