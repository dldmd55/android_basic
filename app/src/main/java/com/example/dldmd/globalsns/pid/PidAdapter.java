package com.example.dldmd.globalsns.pid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.dldmd.globalsns.R;

import java.util.ArrayList;

public class PidAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<PidVO> pidContent;
    Context context;

    public PidAdapter(ArrayList itemList){
        pidContent = itemList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.piditem,parent,false);
        //뷰 그룹에 접근
        context = parent.getContext();
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder recyclerViewHolder, final int position) {

        recyclerViewHolder.userName.setText(pidContent.get(position).userName);
        recyclerViewHolder.nowTime.setText(pidContent.get(position).nowTime);
        recyclerViewHolder.contents.setText(pidContent.get(position).contents);
        recyclerViewHolder.contentsId.setText(pidContent.get(position).contentsId);



        recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context,PidDetail.class);
                intent.putExtra("CONTENTS_INFO",pidContent.get(position).toString());
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return pidContent.size();
    }
}
