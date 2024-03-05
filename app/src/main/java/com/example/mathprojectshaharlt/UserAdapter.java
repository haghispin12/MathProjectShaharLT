package com.example.mathprojectshaharlt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    public interface OnItemClickListener{
        void OnItemClickListener(User item);
    }
    private ArrayList<User>users;
    private OnItemClickListener listener;

    public UserAdapter(ArrayList<User>users, OnItemClickListener listener){
        users = this.users;
    }
    public UserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(users.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
TextView UserName;
ImageView UserImg;
    public MyViewHolder(View ItemView){
        super(ItemView);
        UserName = ItemView.findViewById(R.id.UserName);
        UserImg = ItemView.findViewById(R.id.UserImg);
    }
    public void bind(final User item, final OnItemClickListener listener){
        UserName.setText(item.getName());
        UserImg.setImageResource(item.setBitmap(item.getBitmap()););
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnItemClickListener(item);
            }
        });
    }

}
}




