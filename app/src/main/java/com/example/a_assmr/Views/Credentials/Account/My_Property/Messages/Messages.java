package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a_assmr.CommonDir.ActiveUserSharedPref;
import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.Controller.MessageController;
import com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.Model.MessagesModelContainer;
import com.example.a_assmr.Views.Credentials.Account.My_Property.ViewProperty.ItemViewModel;

public class Messages extends Fragment {
    RecyclerView rvChatMessages;
    SwipeRefreshLayout swipeRefreshLayout;
    MessageController messageController;
    ActiveUserSharedPref activeUserSharedPref;
    ItemViewModel itemViewModel;
    Context context;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.accnt_messages, container, false);

        initForm(view);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);

                fetchMessages();
            }
        });
        return view;
    }
    private void initForm(View view) {
        itemViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(ItemViewModel.class);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        rvChatMessages = view.findViewById(R.id.rvChatMessages);

        messageController = new MessageController(requireActivity(), requireActivity());
        activeUserSharedPref = new ActiveUserSharedPref(requireActivity());

        fetchMessages();
    }
    private void fetchMessages() {
        final String userEmail = activeUserSharedPref.activeUserEmail();
        final int userID = activeUserSharedPref.activeUserID();

        messageController.getMessages(userEmail, userID);

        itemViewModel.getSelectedItem().observe((LifecycleOwner) context, item -> {
            Object obj = item.getCertainGenericClass();
            if(obj instanceof MessagesModelContainer) {
                if(((MessagesModelContainer) obj).getCode() == 200) {
                    rvChatMessages.setLayoutManager(new LinearLayoutManager(context));
                    MessagesAdapter adapter = new MessagesAdapter(context, ((MessagesModelContainer) obj).getMessagesModelList());
                    rvChatMessages.setAdapter(adapter);
                }
                else
                    Toast.makeText(requireActivity(), "Someting went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    } // this hit to API and get the latests messages of the active user(s)

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
