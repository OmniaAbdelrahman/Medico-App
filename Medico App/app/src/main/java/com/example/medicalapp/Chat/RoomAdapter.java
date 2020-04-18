package com.example.medicalapp.Chat;



import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalapp.HomeActivity;
import com.example.medicalapp.LogInActivity;
import com.example.medicalapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>  {



    ArrayList<RoomInfo> roomlist;

    Fragment f;
    String name;
    public RoomAdapter(ArrayList<RoomInfo> roomlist,Fragment f,String name)
    {


        this.roomlist = roomlist;
this.name=name;
        this.f=f;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.roon_item, parent, false);
        RoomViewHolder rcv = new RoomViewHolder(layoutView);
        return rcv;


    }

    @Override
    public void onBindViewHolder(@NonNull final RoomViewHolder holder, final int position) {
        holder.RoomName.setText(roomlist.get(position).getName());
        holder.imageView.setImageResource(0);

        holder.imageView.setImageResource(roomlist.get(position).getPhoto());


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f.getFragmentManager().beginTransaction().replace(R.id.frame_container,new ChatFragment(roomlist.get(position).getName(),roomlist.get(position).getPhoto(),name)).addToBackStack(null).commit();





            }
        });
    }

    @Override
    public int getItemCount() {
        return roomlist.size();
    }





    class RoomViewHolder extends RecyclerView.ViewHolder{
        TextView RoomName;
        ImageView imageView;
        ConstraintLayout item;



        RoomViewHolder(View view){
            super(view);
            item=view.findViewById(R.id.room_item);
            RoomName = view.findViewById(R.id.roomname_txt);
            imageView=view.findViewById(R.id.imageRoom);

        }
    }


}