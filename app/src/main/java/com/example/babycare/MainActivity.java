package com.example.babycare;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

//import java.util.Date;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private ImageButton last_alerts_button;
    private ImageButton alert_button;
    public static String DATE = null;
    public static DatabaseHandler dbHandler = null;
    SQLiteDatabase db = null;
    public static ContentValues values = null;
    BluetoothAdapter bluetoothAdapter = null;
    public ImageView btview = null;
    Uri notification = null;
    Ringtone r = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        setContentView(R.layout.activity_main);
        dbHandler = new DatabaseHandler(this);
        db = dbHandler.getWritableDatabase();
        values = new ContentValues();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btview = (ImageView) findViewById(R.id.btimage);
        btview.setImageResource(R.drawable.btgrey);

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.registerReceiver(mReceiver, filter);


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

            @Override
            public void onClick(View v) {

            }
        });
    }

    //The BroadcastReceiver that listens for bluetooth broadcasts
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            String name = device.getName();
            if(name.equals("AirPods ")) {

                if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                    //Device is now connected
                    btview.setImageResource(R.drawable.btblue);
                } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                    //Device has disconnected
                    btview.setImageResource(R.drawable.btgrey);
                    disconnected();

                }
            }
        }
    };

    private void disconnected(){
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

    private void ringtone() {
        try {
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

