package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinalBillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_bill);

        // Retrieve the data passed from OnlinePaymentActivity
        String name = getIntent().getStringExtra("user_name");
        String age = getIntent().getStringExtra("user_age");
        String mobile = getIntent().getStringExtra("user_mobile");
        String address = getIntent().getStringExtra("user_address");
        String bank = getIntent().getStringExtra("user_bank");
        String branch = getIntent().getStringExtra("user_branch");
        String cardType = getIntent().getStringExtra("user_card_type");
        String cardNumber = getIntent().getStringExtra("user_card_number");
        double roomPrice = getIntent().getDoubleExtra("room_price", 0);

        // Find views
        TextView finalBillTitle = findViewById(R.id.finalBillTitle);
        TextView userDetailsTextView = findViewById(R.id.userDetailsTextView);
        TextView totalBillTextView = findViewById(R.id.totalBillTextView);
        Button finishButton = findViewById(R.id.finishButton);

        // Prepare the user details
        String userDetails = "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Mobile: " + mobile + "\n" +
                "Address: " + address + "\n\n" +
                "Bank: " + bank + "\n" +
                "Branch: " + branch + "\n" +
                "Card Type: " + cardType + "\n" +
                "Card Number: " + cardNumber;

        // Set user details text
        userDetailsTextView.setText(userDetails);

        // Set the total bill text
        totalBillTextView.setText("Total Bill: $" + roomPrice);

        // Set up the finish button to navigate to the main page (or your "display page")
        finishButton.setOnClickListener(v -> {
            // Create an Intent to go back to the MainActivity (or your specific display page)
            Intent intent = new Intent(FinalBillActivity.this, DisplayActivity.class);
            // You can also add flags to prevent going back to this activity by using Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish(); // Finish FinalBillActivity to remove it from the back stack
        });
    }
}
