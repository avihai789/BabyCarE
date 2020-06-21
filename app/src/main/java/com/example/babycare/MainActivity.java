package com.example.babycare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    public static String DATE = null;
    public static DatabaseHandler dbHandler = null;
    SQLiteDatabase db = null;
    public static ContentValues values = null;
    BluetoothAdapter bluetoothAdapter = null;
    @SuppressLint("StaticFieldLeak")
    public static ImageView btview = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        dbHandler = new DatabaseHandler(this);
        db = dbHandler.getWritableDatabase();
        values = new ContentValues();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btview = findViewById(R.id.btimage);

        startService(new Intent(MainActivity.this, Service.class));

        final Intent i = new Intent(MainActivity.this,AlertActivity.class);

        ImageButton alert_button = findViewById(R.id.alert_btn);
        ImageButton last_alerts_button = findViewById(R.id.last_alert_btn);

        last_alerts_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

        alert_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }


}

