package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.ChatRoom;

public class Messages extends Fragment {
    Button btnTests;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.accnt_messages, container, false);

        btnTests = view.findViewById(R.id.btnTests);
        btnTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_open_chat_room = new Intent(requireActivity(), ChatRoom.class);
                requireContext().startActivity(i_open_chat_room);
            }
        });
        return view;
    }
}
