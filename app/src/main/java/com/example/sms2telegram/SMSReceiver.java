package com.example.sms2telegram;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                for (Object pdu : pdus) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                    String message = sms.getMessageBody();
                    Matcher matcher = Pattern.compile("\\b\\d{6}\\b").matcher(message);
                    if (matcher.find()) {
                        String code = matcher.group();
                        SharedPreferences prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
                        String token = prefs.getString("token", "");
                        String chatId = prefs.getString("chat_id", "");
                        TelegramSender.send(token, chatId, code);
                    }
                }
            }
        }
    }
}
