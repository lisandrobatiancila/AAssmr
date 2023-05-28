package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Controller.ChatRoomController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.GetMessageModelContainer;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.GetMessagesModel;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model.SendIMGMessageModel;
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
    String inboundUser = "active-user@gmail.com", outboundUser = "other-user@gmail.com";
    String messageType = "text-only"; // this can be ['text-only', 'image-only']
    ActiveUserSharedPref activeUserSharedPref;
    ItemViewModel itemViewModel;
    ActivityResultLauncher<Intent> uploadIMG;
    Intent imageDATA;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accnt_chat_room);

        initForm();

        chatRoomController.getMessages(inboundUser, outboundUser);

        uploadIMG = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            imageDATA = result.getData();

                            SendIMGMessageModel imgMessageModel = new SendIMGMessageModel(userID, inboundUser, outboundUser, imageDATA);
                            chatRoomController.sendIMAGEMessage(imgMessageModel);
                        }
                    }
                }
        );
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = edtMessage.getText().toString();

                if(!message.equals("")) {
                    edtMessage.setText("");
                    SendMessageModel sendMessageModel = new SendMessageModel(userID, inboundUser, outboundUser, message, "text-only");
                    chatRoomController.sendTEXTMessage(sendMessageModel);
                }
            }
        }); // btnSendMessage

        btnUploadIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkStoragePermission()) {
                    Intent i_open_gallery = new Intent(Intent.ACTION_GET_CONTENT);
                    i_open_gallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    i_open_gallery.setType("image/*");

                    uploadIMG.launch(i_open_gallery);
                }
            }
        });

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
        Bundle bundle = getIntent().getExtras();

        rvMessages = findViewById(R.id.rvMessages);
        btnUploadIMG = findViewById(R.id.btnUploadIMG); btnSendMessage = findViewById(R.id.btnSendMessage);
        edtMessage = findViewById(R.id.edtMessage);

        activeUserSharedPref = new ActiveUserSharedPref(this);
        userID = activeUserSharedPref.activeUserID();
        inboundUser = activeUserSharedPref.activeUserEmail();

        outboundUser = bundle.getString("message_sender");

        chatRoomController = new ChatRoomController(this, this);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
    }
    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return result;
    }
}
