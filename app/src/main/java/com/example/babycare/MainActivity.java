package com.example.babycare;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageButton last_alerts_button;
    private ImageButton alert_button;
    public static String DATE = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview2 = findViewById(R.id.alerts_dates_list2);
        final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.<String>asList());
        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (MainActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        listview2.setAdapter(adapter);

        alert_button = findViewById(R.id.alert_btn);
        last_alerts_button = findViewById(R.id.last_alert_btn);

        last_alerts_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlastalertsactivity();
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
                Intent i = new Intent(MainActivity.this,AlertActivity.class);
                Date today = new Date();
                DATE = today.toString();
                i.putExtra(DATE, today.toString());
                //startActivity(i);
                //ListElementsArrayList.add(DATE);
                //adapter.notifyDataSetChanged();

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

    public void openlastalertsactivity() {
        Intent intent = new Intent(this, AlertActivity.class);
        startActivity(intent);
    }
}

