package com.example.babycare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Main class
 * Responsible for creating and managing the buttons in the main page.
 * Tells if there is a current bluetooth connection.
 */
public class MainActivity extends AppCompatActivity implements NewMessageDialog.NewMessageDialogListener {

    public static String DATE = null;
    public static DatabaseHandler dbHandler = null;
    SQLiteDatabase db = null;
    public static ContentValues values = null;
    BluetoothAdapter bluetoothAdapter = null;
    @SuppressLint("StaticFieldLeak")
    public static ImageView btview = null;
    Intent dialogIntent = null;

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

        final Intent alertIntent = new Intent(MainActivity.this, AlertActivity.class);
        dialogIntent = new Intent(MainActivity.this, DialogActivity.class);

        Button alert_button = findViewById(R.id.alert_btn);
        Button last_alerts_button = findViewById(R.id.last_alert_btn);
        Button changeAlertBtn = findViewById(R.id.change_btn);

        last_alerts_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(alertIntent);
            }
        });

        changeAlertBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NewMessageDialog newMessageDialog = new NewMessageDialog();
                newMessageDialog.show(getSupportFragmentManager(), "example");

            }
        });

        alert_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(dialogIntent);
            }
        });
    }

    /**
     * function that gets the title and message that the user wants to appear in the alert
     * @param title the title that the user put
     * @param message the message that the user put
     */
    @Override
    public void applyTexts(String title, String message) {

        dialogIntent.putExtra("newtitle", title);
        dialogIntent.putExtra("newmessage", message);
    }
}

