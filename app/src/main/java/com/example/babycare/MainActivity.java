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
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageButton last_alerts_button;
    private ImageButton alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alert = findViewById(R.id.alert_btn);
        last_alerts_button = findViewById(R.id.last_alert_btn);

        last_alerts_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlastalertsactivity();
            }
        });

        alert.setOnClickListener(new View.OnClickListener() {

            //public void onClick(View v) {
            //    ListElementsArrayList.add();
            //    adapter.notifyDataSetChanged();
            //}


            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);

            public void ringtone(){
                try {
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override

            public void onClick(View v) {
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

    public void openlastalertsactivity(){
        Intent intent = new Intent(this, AlertActivity.class);
        startActivity(intent);
    }






}

