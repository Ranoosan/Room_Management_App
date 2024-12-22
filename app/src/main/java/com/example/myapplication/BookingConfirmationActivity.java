package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookingConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        // Find the views
        TextView confirmationTitle = findViewById(R.id.confirmationTitle);
        TextView bookingDetailsTextView = findViewById(R.id.bookingDetailsTextView);
        Button finishButton = findViewById(R.id.finishButton);

        // Get payment method from the Intent
        String paymentMethod = getIntent().getStringExtra("payment_method");
        bookingDetailsTextView.setText("Your booking has been confirmed. Please pay " + paymentMethod + ".");

        // Finish Button click listener
        finishButton.setOnClickListener(v -> {
            // Close the booking confirmation activity and return to home screen or main activity
            finish();
        });
    }
}
