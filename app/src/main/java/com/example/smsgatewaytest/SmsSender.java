package com.example.smsgatewaytest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SmsSender {

    public static String sendSms(String newtel, String message1) {
        String userId = "";
        String apiKey = "";
        String senderId = "";

        try {
            // Construct the URL
            String urlString = "https://app.notify.lk/api/v1/send?user_id=" + userId
                    + "&api_key=" + apiKey
                    + "&sender_id=" + senderId
                    + "&to=" + newtel
                    + "&message=" + message1;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            // Get the response
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Return result
                return "Success: " + response.toString();
            } else {
                return "Failed: Response code " + responseCode;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed: Exception occurred";
        }
    }

}
