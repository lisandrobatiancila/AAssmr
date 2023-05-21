package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;

public class ChatRoomHolder extends RecyclerView.ViewHolder {
    LinearLayout linearOutboundMessages;
    TextView txtOutboundMessages;
    ImageView imgOutboundMessages;

    LinearLayout linearInboundMessages;
    TextView txtInboundMessages;
    ImageView imgInboundMessages;
    public ChatRoomHolder(@NonNull View itemView) {
        super(itemView);

        linearOutboundMessages = itemView.findViewById(R.id.linearOutboundMessage);
        txtOutboundMessages = itemView.findViewById(R.id.txtOutboundMessage);
        imgOutboundMessages = itemView.findViewById(R.id.imgOutboundMessage);

        linearInboundMessages = itemView.findViewById(R.id.linearInboundMessage);
        txtInboundMessages = itemView.findViewById(R.id.txtInboundMessage);
        imgInboundMessages = itemView.findViewById(R.id.imgInboundMessage);
    }
}
