package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Controller.ChatRoomController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.GetMessageModelContainer;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.GetMessagesModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.SendMessageModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom extends AppCompatActivity {
    ImageButton btnUploadIMG, btnSendMessage;
    EditText edtMessage;
    RecyclerView rvMessages;
    ChatRoomController chatRoomController;
    int userID;
    String inboundUser = "klent@gmail.com", outboundUser = "dale@gmail.com";
    String messageType = "text-only"; // this can be ['text-only', 'image-only']
    ActiveUserSharedPref activeUserSharedPref;
    ItemViewModel itemViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accnt_chat_room);

        initForm();

        chatRoomController.getMessages(inboundUser, outboundUser);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = edtMessage.getText().toString();

                if(!message.equals("")) {
                    edtMessage.setText("");
                    SendMessageModel sendMessageModel = new SendMessageModel(userID, inboundUser, outboundUser, message, "text-only");
                    chatRoomController.sendMessage(sendMessageModel);
                }
            }
        }); // btnSendMessage
        itemViewModel.getSelectedItem().observe(this, item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof GetMessageModelContainer) {
                List<GetMessagesModel> modelMessagesLists = new ArrayList<>();
                for(GetMessagesModel gmm : ((GetMessageModelContainer) obj).getUserMessages()) {
                    modelMessagesLists.add(gmm);
                }
                ChatRoomAdapter cra = new ChatRoomAdapter(this, modelMessagesLists, inboundUser);
                rvMessages.setLayoutManager(new LinearLayoutManager(this));
                rvMessages.setAdapter(cra);
            }
        });
    }
    private void initForm() {
        rvMessages = findViewById(R.id.rvMessages);
        btnUploadIMG = findViewById(R.id.btnUploadIMG); btnSendMessage = findViewById(R.id.btnSendMessage);
        edtMessage = findViewById(R.id.edtMessage);

        activeUserSharedPref = new ActiveUserSharedPref(this);
        userID = activeUserSharedPref.activeUserID();
        inboundUser = activeUserSharedPref.activeUserEmail();

        chatRoomController = new ChatRoomController(this, this);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
    }
}
