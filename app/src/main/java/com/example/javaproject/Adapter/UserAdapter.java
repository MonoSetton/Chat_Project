package com.example.javaproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.javaproject.MessageActivity;
import com.example.javaproject.Model.Users;
import com.example.javaproject.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Viewholder> {

    private Context context;
    private List<Users> mUsers;

    //Constructor

    public UserAdapter(Context context, List<Users> mUsers) {
        this.context = context;
        this.mUsers = mUsers;
    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,
                parent,
                false);
        return new UserAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        Users users = mUsers.get(position);
        holder.username.setText(users.getUsername());

        if (users.getImageURL().equals("default"))
        {
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        }
        else
        {
            //Adding Glide Library
            Glide.with(context)
                    .load(users.getImageURL())
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener((v) -> {
                Intent i = new Intent(context, MessageActivity.class);
                i.putExtra("userid", users.getId());
                context.startActivity(i);

        });





    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView imageView;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.textView30);
            imageView = itemView.findViewById(R.id.imageView30);


        }
    }






}

