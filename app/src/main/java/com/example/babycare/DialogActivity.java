package com.example.babycare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Date;

public class DialogActivity extends AppCompatActivity {

    protected static final int NOTIFICATION_ID = 1337;
    private static String TAG = "Service";
    @SuppressLint("StaticFieldLeak")
    private static Service mCurrentService;
    private int counter = 0;
    public ImageView btview = null;
    Uri notification = null;
    Ringtone r = null;
    public static String DATE = null;
    SQLiteDatabase db = null;
    public static ContentValues values = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dialog);

        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);

        DatabaseHandler dbHandler = new DatabaseHandler(this);
        db = dbHandler.getWritableDatabase();
        values = new ContentValues();

        Date today = new Date();
        DATE = today.toString();
        values.put("DATE", DATE);
        long row = db.insert("History", null, values);
        System.out.println(row);

        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
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


    private void ringtone() {
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
