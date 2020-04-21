package com.example.babycare;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

//import java.util.Date;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private ImageButton last_alerts_button;
    private ImageButton alert_button;
    public static String DATE = null;
    public static DatabaseHandler dbHandler = null;
    SQLiteDatabase db = null;
    public static ContentValues values = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DatabaseHandler(this);
        db = dbHandler.getWritableDatabase();
        values = new ContentValues();

        final Intent i = new Intent(MainActivity.this,AlertActivity.class);

        alert_button = findViewById(R.id.alert_btn);
        last_alerts_button = findViewById(R.id.last_alert_btn);

        last_alerts_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

        alert_button.setOnClickListener(new View.OnClickListener() {

            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            private void ringtone() {
                try {
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onClick(View v) {
                Date today = new Date();
                DATE = today.toString();
                values.put("DATE", DATE);
                long row = db.insert("History", null, values);
                System.out.println(row);


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setTitle("Kid forgotten");
                builder.setMessage("Return to the car now!!");
                ringtone();
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        r.stop();
                    }
                });
                builder.show();
            }
        });
    }
}

