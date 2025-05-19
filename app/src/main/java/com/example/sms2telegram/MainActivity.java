package com.example.sms2telegram;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private EditText tokenEditText, chatIdEditText;
    private Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tokenEditText = findViewById(R.id.editTextToken);
        chatIdEditText = findViewById(R.id.editTextChatId);
        saveButton = findViewById(R.id.buttonSave);
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        tokenEditText.setText(prefs.getString("token", ""));
        chatIdEditText.setText(prefs.getString("chat_id", ""));
        saveButton.setOnClickListener(v -> {
            prefs.edit()
                .putString("token", tokenEditText.getText().toString())
                .putString("chat_id", chatIdEditText.getText().toString())
                .apply();
        });
    }
}
