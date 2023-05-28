package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.Common;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.GetMessagesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomHolder> {
    Context context;
    List<GetMessagesModel> messageLists;
    String activeUserEmail;
    Common common;
    LayoutInflater inflater;

    public ChatRoomAdapter(Context context, List<GetMessagesModel> messageLists, String activeUserEmail) {
        this.context = context;
        this.messageLists = messageLists;
        this.activeUserEmail = activeUserEmail;
        this.common = new Common();
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ChatRoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatRoomHolder(inflater.inflate(R.layout.accnt_chat_room_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomHolder holder, int position) {
        String emailINLists = messageLists.get(position).getMessage_sender();
        // messages of active user goes to right
        // messages of outer user goes to left
        if(activeUserEmail.equals(emailINLists)) {
            holder.linearOutboundMessages.setVisibility(View.GONE);
            if(messageLists.get(position).getMessage_type().equals("text-only")) {
                holder.imgInboundMessages.setVisibility(View.GONE);
                holder.txtInboundMessages.setText(messageLists.get(position).getMessage());
            }
            else if(messageLists.get(position).getMessage_type().equals("image-only")) {
                holder.txtInboundMessages.setVisibility(View.GONE);
                String image = messageLists.get(position).getMessage();
                String[] imageARR = image.substring(1, image.length()-1).split(",");
                Picasso.get().load(common.getApiURI()+"/"+imageARR[0].replaceAll("\"", "")).resize(200, 200).placeholder(R.drawable.ic_launcher_background)
                        .into(holder.imgInboundMessages);

            }
        } // end of active user messages; means inboundMessages
        else {
            holder.linearInboundMessages.setVisibility(View.GONE);
            if(messageLists.get(position).getMessage_type().equals("text-only")) {
                holder.imgOutboundMessages.setVisibility(View.GONE);
                holder.txtOutboundMessages.setText(messageLists.get(position).getMessage());
            }
            else if(messageLists.get(position).getMessage_type().equals("image-only")) {
                holder.txtOutboundMessages.setVisibility(View.GONE);

                String image = messageLists.get(position).getMessage();
                String[] imageARR = image.substring(1, image.length()-1).split(",");

                Picasso.get().load(common.getApiURI()+"/"+imageARR[0].replaceAll("\"", "")).placeholder(R.drawable.ic_launcher_background)
                        .into(holder.imgOutboundMessages);
            }
        } // end of other user messages; means outboundMessages
    }

    @Override
    public int getItemCount() {
        return messageLists.size();
    }
}
