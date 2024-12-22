package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        // Retrieve room price and other details passed from BookingDetailsActivity
        double roomPrice = getIntent().getDoubleExtra("room_price", 0);  // Get the room price
        String roomName = getIntent().getStringExtra("room_name");  // Get room name if needed

        // Find buttons and other views in the layout
        Button cashOnHandButton = findViewById(R.id.cashOnHandButton);
        Button onlinePaymentButton = findViewById(R.id.onlinePaymentButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        TextView roomPriceTextView = findViewById(R.id.roomPriceTextView); // Assuming a TextView to display price

        // Display the room price
        roomPriceTextView.setText("Room Price: $" + roomPrice);

        // Cash on Hand Button click listener
        cashOnHandButton.setOnClickListener(v -> {
            // Handle cash on hand selection
            Intent intent = new Intent(PaymentMethodActivity.this, BookingConfirmationActivity.class);
            intent.putExtra("payment_method", "Cash on Hand");
            intent.putExtra("room_price", roomPrice);  // Pass the room price to BookingConfirmationActivity
            intent.putExtra("room_name", roomName);  // Optionally, pass room name
            startActivity(intent);
        });

        // Online Payment Button click listener
        onlinePaymentButton.setOnClickListener(v -> {
            // Handle online payment selection
            Intent intent = new Intent(PaymentMethodActivity.this, OnlinePaymentActivity.class);
            intent.putExtra("room_price", roomPrice);  // Pass the room price to OnlinePaymentActivity
            intent.putExtra("room_name", roomName);  // Optionally, pass room name
            startActivity(intent);
        });

        // Cancel Button click listener
        cancelButton.setOnClickListener(v -> {
            // Go back to the previous screen
            finish();
        });
    }
}
