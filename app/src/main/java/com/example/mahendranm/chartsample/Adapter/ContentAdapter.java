package com.example.mahendranm.chartsample.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mahendranm.chartsample.R;


import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyHolder> {

    Activity activity;

    Context context;
    List<String> contentText;

    public ContentAdapter(@NonNull Context context, List<String> contentText, Activity activity) {
        this.context = context;
        this.contentText = contentText;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contentlist,parent,false);
        return new MyHolder(view, contentText);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.txt_list.setText(contentText.get(position));

    }

    @Override
    public int getItemCount() {
        return contentText.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder  {

        TextView txt_list;
        List<String> _contentText;

        public MyHolder(@NonNull View itemView, List<String> contentText) {
            super(itemView);
            txt_list= itemView.findViewById(R.id.iconList);
            this._contentText = contentText;
        }



    }

}
