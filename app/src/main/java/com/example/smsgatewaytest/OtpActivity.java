package com.example.smsgatewaytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OtpActivity extends AppCompatActivity {

    private EditText otpEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otpEditText = findViewById(R.id.otp_edit_text);
        Button verifyOtpButton = findViewById(R.id.verify_otp_button);

        verifyOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOtp = otpEditText.getText().toString();
                if (enteredOtp.equals(MainActivity.currentOTP)) {
                    Toast.makeText(OtpActivity.this, "OTP verified successfully!", Toast.LENGTH_SHORT).show();
                    // Proceed with the next steps
                } else {
                    Toast.makeText(OtpActivity.this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}