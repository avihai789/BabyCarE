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

/**
 * This class is responsible for the alert that pops when the bluetooth connection is gone
 * or if the alert button was clicked.
 */
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
    String finalTitle = null;
    String finalMessage = null;

    /**
     * This function pops the alert and make the ringtone start playing.
     * it also takes the current time and put it in the data base.
     * @param savedInstanceState bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        finalTitle = "Kid forgotten";
        finalMessage = "Return to the car now!!";

        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);

        DatabaseHandler dbHandler = new DatabaseHandler(this);
        db = dbHandler.getWritableDatabase();
        values = new ContentValues();

        Date today = new Date();
        DATE = today.toString().substring(0, 16);
        values.put("DATE", DATE);
        long row = db.insert("History", null, values);
        System.out.println(row);

        String temp;
        Intent intent = getIntent();

        temp = intent.getStringExtra("newtitle");
        if(temp != null)
            finalTitle = temp;

        temp = intent.getStringExtra("newmessage");
        if(temp != null)
            finalMessage = temp;

        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
        builder.setCancelable(false);
        builder.setTitle(finalTitle);
        builder.setMessage(finalMessage);
        ringtone();
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                r.stop();
                onBackPressed();
            }
        });
        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        r.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        r.stop();
    }

    private void ringtone() {
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
