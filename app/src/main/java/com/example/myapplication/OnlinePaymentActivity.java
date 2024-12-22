package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OnlinePaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_payment);

        // Find views
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText ageEditText = findViewById(R.id.ageEditText);
        EditText mobileNumberEditText = findViewById(R.id.mobileNumberEditText);
        EditText addressEditText = findViewById(R.id.addressEditText);
        EditText bankEditText = findViewById(R.id.bankEditText);
        EditText branchEditText = findViewById(R.id.branchEditText);
        EditText cardTypeEditText = findViewById(R.id.cardTypeEditText);
        EditText cardNumberEditText = findViewById(R.id.cardNumberEditText);
        TextView totalBillTextView = findViewById(R.id.totalBillTextView);
        Button okButton = findViewById(R.id.okButton);

        // Retrieve the total room price (passed from BookingDetailsActivity)
        double roomPrice = getIntent().getDoubleExtra("room_price", 0);
        totalBillTextView.setText("Total Bill: $" + roomPrice);

        // Set up "Proceed" button to navigate to final receipt page
        okButton.setOnClickListener(v -> {
            // Get user input data
            String name = nameEditText.getText().toString();
            String age = ageEditText.getText().toString();
            String mobile = mobileNumberEditText.getText().toString();
            String address = addressEditText.getText().toString();
            String bank = bankEditText.getText().toString();
            String branch = branchEditText.getText().toString();
            String cardType = cardTypeEditText.getText().toString();
            String cardNumber = cardNumberEditText.getText().toString();

            // Validate inputs
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(address) ||
                    TextUtils.isEmpty(bank) || TextUtils.isEmpty(branch) || TextUtils.isEmpty(cardType) || TextUtils.isEmpty(cardNumber)) {
                Toast.makeText(OnlinePaymentActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidAge(age)) {
                Toast.makeText(OnlinePaymentActivity.this, "Please enter a valid age (18-100)", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidMobileNumber(mobile)) {
                Toast.makeText(OnlinePaymentActivity.this, "Please enter a valid Sri Lankan mobile number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidCardNumber(cardNumber)) {
                Toast.makeText(OnlinePaymentActivity.this, "Please enter a valid 16-digit card number", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create an Intent to pass all data to the final receipt page
            Intent intent = new Intent(OnlinePaymentActivity.this, FinalBillActivity.class);
            intent.putExtra("user_name", name);
            intent.putExtra("user_age", age);
            intent.putExtra("user_mobile", mobile);
            intent.putExtra("user_address", address);
            intent.putExtra("user_bank", bank);
            intent.putExtra("user_branch", branch);
            intent.putExtra("user_card_type", cardType);
            intent.putExtra("user_card_number", cardNumber);
            intent.putExtra("room_price", roomPrice);
            startActivity(intent);
        });
    }

    // Validate the age (between 18 and 100)
    private boolean isValidAge(String age) {
        try {
            int ageInt = Integer.parseInt(age);
            return ageInt >= 18 && ageInt <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate Sri Lankan mobile number (should start with "07" and be 10 digits)
    private boolean isValidMobileNumber(String mobile) {
        return mobile.matches("^07\\d{8}$");
    }

    // Validate card number (16 digits)
    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("^\\d{16}$");
    }
}
