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
    private boolean isChat;



    //Constructor

    public UserAdapter(Context context, List<Users> mUsers, boolean isChat) {
        this.context = context;
        this.mUsers = mUsers;
        this.isChat = isChat;

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

        //Status check
        if (isChat)
        {
            if (users.getStatus().equals("online"))
            {
                holder.imageViewOn.setVisibility(View.VISIBLE);
                holder.imageViewOff.setVisibility(View.GONE);
            }
            else
            {
                holder.imageViewOn.setVisibility(View.GONE);
                holder.imageViewOff.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            holder.imageViewOn.setVisibility(View.GONE);
            holder.imageViewOff.setVisibility(View.GONE);
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
        public ImageView imageViewOn;
        public ImageView imageViewOff;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.textView30);
            imageView = itemView.findViewById(R.id.imageView30);
            imageViewOn = itemView.findViewById(R.id.statusimageOn);
            imageViewOff = itemView.findViewById(R.id.statusimageOFF);


        }
    }






}

