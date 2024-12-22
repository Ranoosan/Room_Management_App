package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);

        EditText emailInput = findViewById(R.id.emailInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        EditText confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                String confirmPassword = confirmPasswordInput.getText().toString().trim();

                // Email validation for Gmail format
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith("@gmail.com")) {
                    Toast.makeText(RegisterActivity.this, "Please enter a valid Gmail address", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (!isValidPassword(password)) {
                    Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters and contain both letters and numbers", Toast.LENGTH_SHORT).show();
                } else if (databaseHelper.isEmailExists(email)) {
                    Toast.makeText(RegisterActivity.this, "Email already registered", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = databaseHelper.addUser(email, password);
                    if (isInserted) {
                        Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        finish(); // Close the activity
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Function to check if the password is valid
    private boolean isValidPassword(String password) {
        // Password must contain at least 6 characters, including letters and numbers
        return password.length() >= 6 && password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*");
    }
}
