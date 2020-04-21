package com.example.babycare;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AlertActivity extends AppCompatActivity {

    private DatabaseHandler dbHandler = null;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView dateList;
    private Button resetBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        resetBtn = findViewById(R.id.eraseBtn);
        dbHandler = MainActivity.dbHandler;
        dateList = findViewById(R.id.alerts_dates_list);
        listItem = new ArrayList<>();


        showContent();

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteData();
                listItem.clear();
                showContent();
            }
        });
    }

    public void showContent(){

        Cursor cursor = dbHandler.viewData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(0));
            }
        }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            dateList.setAdapter(adapter);

    }

}

