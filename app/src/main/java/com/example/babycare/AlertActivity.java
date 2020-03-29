package com.example.babycare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlertActivity extends AppCompatActivity {

    String passedVar=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        ListView listview1 = findViewById(R.id.alerts_dates_list1);
        final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.<String>asList());
        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (AlertActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        listview1.setAdapter(adapter);

        if(MainActivity.DATE!=null) {
            passedVar = getIntent().getStringExtra(MainActivity.DATE);
            ListElementsArrayList.add(MainActivity.DATE);
            adapter.notifyDataSetChanged();
        }



    }
}
