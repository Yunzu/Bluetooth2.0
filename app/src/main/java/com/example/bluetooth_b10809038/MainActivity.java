package com.example.bluetooth_b10809038;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button entry_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entry_button = (Button)findViewById(R.id.entry_button);
        entry_button.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ScannerActivity.class);
            startActivity(intent);
        });
    }
}