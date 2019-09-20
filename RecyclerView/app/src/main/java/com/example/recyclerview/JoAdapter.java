package com.example.recyclerview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JoAdapter extends RecyclerView.Adapter<JoAdapter.ViewHolder> {
    private List<Jo> mJoList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView joImage;

        public ViewHolder(View view){
            super(view);
            joImage=(ImageView)view.findViewById(R.id.jo_pic);
        }
    }

    public JoAdapter(List<Jo> JoList){

        mJoList=JoList;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pic_item,parent,false);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Jo jo=mJoList.get(position);
        holder.joImage.setImageResource(jo.getImageId());
    }

    @Override
    public int getItemCount() {

        return mJoList.size();
    }


}
