package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.ChatRoom;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.Model.MessagesModel;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesHolder> {
    Context context;
    List<MessagesModel> messagesModelList;
    LayoutInflater inflater;

    public MessagesAdapter(Context context, List<MessagesModel> messagesModelList) {
        this.context = context;
        this.messagesModelList = messagesModelList;
        this.inflater = LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public MessagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessagesHolder(inflater.inflate(R.layout.accnt_messages_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesHolder holder, int position) {
        int pos = position;

        holder.txtUserFullname.setText(messagesModelList.get(pos).getFullName());
        holder.txtDate.setText(messagesModelList.get(pos).getMessage_date());
        if(messagesModelList.get(pos).getMessage_type().equals("image-only"))
            holder.txtMessage.setText("Image attached.");
        else
            holder.txtMessage.setText(messagesModelList.get(pos).getMessage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message_sender = messagesModelList.get(pos).getMessage_sender(); // is the other-user
                String message_reciever = messagesModelList.get(pos).getMessage_reciever(); // is the active-user

                Intent i_chat_room = new Intent(context, ChatRoom.class);
                i_chat_room.putExtra("message_sender", message_sender);
                i_chat_room.putExtra("message_reciever", message_reciever);

                context.startActivity(i_chat_room);
            }
        });
    }

    @Override
    public int getItemCount() {

        return messagesModelList.size();
    }
}
