package com.example.dldmd.globalsns.pid;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dldmd.globalsns.R;


public class RecyclerViewHolder extends RecyclerView.ViewHolder{
    public TextView userName;
    public TextView nowTime;
    public TextView contents;
    public TextView contentsId;




    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        userName = (TextView)itemView.findViewById(R.id.userName);
        nowTime = (TextView)itemView.findViewById(R.id.nowTime);
        contents = (TextView)itemView.findViewById(R.id.contents);
        contentsId = (TextView)itemView.findViewById(R.id.contentsId);

    }
}
