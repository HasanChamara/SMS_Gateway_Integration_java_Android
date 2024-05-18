package com.example.smsgatewaytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private EditText messageEditText;
    public static String currentOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberEditText = findViewById(R.id.phone_number);
        messageEditText = findViewById(R.id.message);
        Button sendSmsButton = findViewById(R.id.send_sms_button);

        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter the phone number", Toast.LENGTH_SHORT).show();
                } else {
                    currentOTP = generateOTP();
                    String message = "Your OTP is: " + currentOTP;
                    new SendSmsTask().execute(phoneNumber, message);
                }
            }
        });
    }

    private String generateOTP() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    private class SendSmsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String phoneNumber = params[0];
            String message = params[1];
            return SmsSender.sendSms(phoneNumber, message);
        }

        @Override
        protected void onPostExecute(String result) {
            // Display result to user
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();

            if (result.startsWith("Success")) {
                Intent intent = new Intent(MainActivity.this, OtpActivity.class);
                startActivity(intent);
            }
        }
    }

}