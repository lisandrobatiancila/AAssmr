package com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_assmr.R;
import com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Model.FeedBackModel;

import java.util.List;

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackHolder> {
    Context context;
    List<FeedBackModel> feedBackModelList;
    LayoutInflater inflater;

    public FeedBackAdapter(Context context, List<FeedBackModel> feedBackModelList) {
        this.context = context;
        this.feedBackModelList = feedBackModelList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FeedBackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeedBackHolder(inflater.inflate(R.layout.account_feedbacks_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeedBackHolder holder, int position) {
        holder.txtUsername.setText(feedBackModelList.get(position).getUser_full_name());
        holder.txtDate.setText(feedBackModelList.get(position).getFeedback_date());
        holder.txtUserComments.setText(feedBackModelList.get(position).getUser_feedback());
    }

    @Override
    public int getItemCount() {
        return feedBackModelList.size();
    }
}
