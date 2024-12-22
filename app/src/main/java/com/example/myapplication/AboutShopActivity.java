package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_shop);

        TextView aboutTextView = findViewById(R.id.aboutTextView);
        String aboutText = "Welcome to our shop! We offer the best services for you. " +
                "Our mission is to provide top-quality products and an excellent customer experience.";
        aboutTextView.setText(aboutText);
    }
}
