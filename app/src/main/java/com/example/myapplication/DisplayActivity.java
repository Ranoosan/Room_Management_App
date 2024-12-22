package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // Get the email passed from LoginActivity
        String email = getIntent().getStringExtra("email");

        // Display welcome message
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Welcome, " + email + "!");

        // Buttons
        Button bookingButton = findViewById(R.id.bookingButton);
        Button editDetailsButton = findViewById(R.id.editDetailsButton);
        Button feedbackButton = findViewById(R.id.feedbackButton);
        Button aboutShopButton = findViewById(R.id.aboutShopButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        // Click listeners for buttons
        bookingButton.setOnClickListener(v -> {
            // Navigate to BookingActivity
            Intent intent = new Intent(DisplayActivity.this, BookingActivity.class);
            startActivity(intent);
        });

        editDetailsButton.setOnClickListener(v -> {
            // Navigate to EditDetailsActivity and pass the email
            Intent intent = new Intent(DisplayActivity.this, EditDetailsActivity.class);
            intent.putExtra("email", email); // Pass the email to EditDetailsActivity
            startActivity(intent);
        });

        feedbackButton.setOnClickListener(v -> {
            // Navigate to FeedbackActivity
            Intent intent = new Intent(DisplayActivity.this, FeedbackActivity.class);
            startActivity(intent);
        });

        aboutShopButton.setOnClickListener(v -> {
            // Navigate to AboutShopActivity
            Intent intent = new Intent(DisplayActivity.this, AboutShopActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            // Logout and navigate back to LoginActivity
            Intent intent = new Intent(DisplayActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
        });
    }
}
