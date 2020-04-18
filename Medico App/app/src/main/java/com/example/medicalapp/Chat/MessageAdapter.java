package com.example.medicalapp.Chat;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.medicalapp.R;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    public static final int mes_type_left=1;
    public static final int mes_type_right=0;

    ArrayList<MessageObject> messageList;
    private String sender;

    public MessageAdapter(ArrayList<MessageObject> messageList,String name)
    {
        this.messageList = messageList;
        this.sender=name;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType==mes_type_right){
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);
            MessageViewHolder rcv = new MessageViewHolder(layoutView);
            return rcv;
        }
        else {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, parent, false);
            MessageViewHolder rcv = new MessageViewHolder(layoutView);
            return rcv;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder holder, final int position) {
        holder.mMessage.setText(messageList.get(position).getMessage()+"       ");

        holder.sender.setText(messageList.get(position).senderId);


        if(messageList.get(position).getKind().equals("false")){
            holder.imageButton.setVisibility(View.GONE);}

        else {
            holder.imageButton.setVisibility(View.VISIBLE); }
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImageViewer.Builder(v.getContext(), messageList.get(position).getMediaUrlList())
                        .setStartPosition(0)
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }





    class MessageViewHolder extends RecyclerView.ViewHolder{
        TextView mMessage,
                date,
        sender;

        ImageButton imageButton;

        MessageViewHolder(View view){
            super(view);

            mMessage = view.findViewById(R.id.show_message);
           imageButton=(ImageButton) view.findViewById(R.id.image);

           sender=(TextView)view.findViewById(R.id.send_message);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if(sender.equals(messageList.get(position).senderId)){

            return  mes_type_right;
        }
        else
        {

            return mes_type_left;
        }

    }
}