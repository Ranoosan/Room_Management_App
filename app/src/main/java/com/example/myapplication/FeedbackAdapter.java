package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {

    private final List<String> feedbackList;

    public FeedbackAdapter(List<String> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        holder.feedbackTextView.setText(feedbackList.get(position));
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        TextView feedbackTextView;

        FeedbackViewHolder(View itemView) {
            super(itemView);
            feedbackTextView = itemView.findViewById(android.R.id.text1);
        }
    }
}
