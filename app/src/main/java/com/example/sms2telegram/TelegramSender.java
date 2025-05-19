package com.example.sms2telegram;
import android.os.StrictMode;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
public class TelegramSender {
    public static void send(String token, String chatId, String message) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        try {
            URL url = new URL("https://api.telegram.org/bot" + token + "/sendMessage");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            String payload = "chat_id=" + chatId + "&text=" + message;
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(payload);
            writer.flush();
            writer.close();
            conn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
