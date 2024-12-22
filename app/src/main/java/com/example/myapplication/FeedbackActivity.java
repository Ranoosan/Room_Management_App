package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeedbackActivity extends AppCompatActivity {

    private FeedbackDatabaseHelper databaseHelper;
    private FeedbackAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        EditText feedbackInput = findViewById(R.id.feedbackInput);
        Button submitButton = findViewById(R.id.submitButton);
        RecyclerView feedbackRecyclerView = findViewById(R.id.feedbackRecyclerView);

        // Initialize database helper
        databaseHelper = new FeedbackDatabaseHelper(this);

        // Set up RecyclerView
        List<String> feedbackList = databaseHelper.getAllFeedback();
        adapter = new FeedbackAdapter(feedbackList);
        feedbackRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedbackRecyclerView.setAdapter(adapter);

        // Submit button click listener
        submitButton.setOnClickListener(v -> {
            String feedback = feedbackInput.getText().toString().trim();
            if (!feedback.isEmpty()) {
                // Save feedback to database
                databaseHelper.addFeedback(feedback);

                // Refresh the RecyclerView
                feedbackList.add(0, feedback); // Add the new feedback at the top
                adapter.notifyItemInserted(0);
                feedbackRecyclerView.scrollToPosition(0);

                // Clear input field
                feedbackInput.setText("");
            }
        });
    }
}
