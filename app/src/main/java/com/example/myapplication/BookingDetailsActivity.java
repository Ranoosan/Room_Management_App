package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        // Retrieve room details passed from RoomAdapter
        String roomName = getIntent().getStringExtra("room_name");
        double roomPrice = getIntent().getDoubleExtra("room_price", 0);
        String roomDescription = getIntent().getStringExtra("room_description");

        // Find views
        TextView roomNameTextView = findViewById(R.id.roomNameTextView);
        TextView roomPriceTextView = findViewById(R.id.roomPriceTextView);
        TextView roomDescriptionTextView = findViewById(R.id.roomDescriptionTextView);
        Button bookNowButton = findViewById(R.id.bookNowButton);

        // Set the text views with room details
        roomNameTextView.setText(roomName);
        roomPriceTextView.setText("$" + roomPrice);
        roomDescriptionTextView.setText(roomDescription);

        // Add functionality to the "Book Now" button
        bookNowButton.setOnClickListener(v -> {
            // Create an intent to navigate to OnlinePaymentActivity
            Intent intent = new Intent(BookingDetailsActivity.this, PaymentMethodActivity.class);

            // Pass the room details to the next activity
            intent.putExtra("room_name", roomName);
            intent.putExtra("room_price", roomPrice);
            intent.putExtra("room_description", roomDescription);

            // Start the OnlinePaymentActivity
            startActivity(intent);
        });
    }
}
