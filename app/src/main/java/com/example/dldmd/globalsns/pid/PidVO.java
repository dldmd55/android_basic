package com.example.dldmd.globalsns.pid;

import java.util.UUID;

public class PidVO {

    String userId;
    String userName;
    String contents;
    String nowTime;
    String contentsId;

    //조회수,추천수
    int inqCount=0;
    int recommendCount=0;

    public PidVO(String userId,String userName,String contents,String nowTime,String contentsId){

        this.userId = userId;
        this.userName = userName;
        this.contents = contents;
        this.nowTime = nowTime;
        this.contentsId = contentsId;
    }


    public String pidVOJson(){
        String returnPid=null;
        returnPid = "[{'userId':'"+this.userId+"','userName':'"+this.userName+"','contents':'"+this.contents+"','nowTime':'"+this.nowTime+"','contentsId':'"+this.contentsId+"'}]";

        return returnPid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public String getContentsId() {
        return contentsId;
    }

    public void setContentsId(String contentsId) {
        this.contentsId = contentsId;
    }

    public int getInqCount() {
        return inqCount;
    }

    public void setInqCount(int inqCount) {
        this.inqCount = inqCount;
    }

    public int getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }
}
