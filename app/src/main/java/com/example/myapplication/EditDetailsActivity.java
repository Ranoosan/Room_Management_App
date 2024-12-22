package com.example.myapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditDetailsActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private String loggedInEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Get the email passed from DisplayActivity
        loggedInEmail = getIntent().getStringExtra("email"); // Retrieve the email passed from DisplayActivity

        // Find views
        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        Button saveButton = findViewById(R.id.saveButton); // Save button to update details

        // Fetch and display user details based on the email
        Cursor cursor = dbHelper.getUserDetails(loggedInEmail);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));

            // Set the values to the EditText fields
            emailInput.setText(email);
            passwordInput.setText(password);

            cursor.close();
        } else {
            Toast.makeText(this, "Failed to retrieve user details!", Toast.LENGTH_SHORT).show();
        }

        // Enable editing for the fields
        emailInput.setEnabled(true);
        passwordInput.setEnabled(true);

        // Set a click listener for the save button
        saveButton.setOnClickListener(v -> {
            // Get the updated email and password from the input fields
            String newEmail = emailInput.getText().toString();
            String newPassword = passwordInput.getText().toString();

            // Validate the inputs (optional, but recommended)
            if (newEmail.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(EditDetailsActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Update the user details in the database
                boolean isUpdated = dbHelper.updateUserDetails(loggedInEmail, newEmail, newPassword);
                if (isUpdated) {
                    Toast.makeText(EditDetailsActivity.this, "Details updated successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity and return to the previous screen
                } else {
                    Toast.makeText(EditDetailsActivity.this, "Failed to update details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
