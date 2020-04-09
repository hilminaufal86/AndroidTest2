package com.example.app_test1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventActivity extends AppCompatActivity {
    String username;
    int guestID;
    String guestName;
    Integer[] EventIdArray = {1,2,3,4};
    String[] EventNameArray = {"Event 1","Event 2", "Event 3", "Event 4"};
    String[] EventDateArray = {"April 4 2020","April 5 2020","April 8 2020", "April 12 2020"};
    Integer[] EventImageArray = {
            R.drawable.event1,
            R.drawable.event2,
            R.drawable.event3,
            R.drawable.event4
    };

    ListView EventListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_three);

        final Intent intent = getIntent();
        username = intent.getStringExtra("USERNAME");
        guestID = intent.getIntExtra("GUEST ID",-1);
        guestName = intent.getStringExtra("GUEST NAME");

        EventListAdapter eventAdapter = new EventListAdapter(this,EventIdArray,EventNameArray,EventImageArray,EventDateArray);

        EventListView = findViewById(R.id.event_view);
        EventListView.setAdapter(eventAdapter);

        EventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intents = new Intent(EventActivity.this, MainActivity.class);
                Integer Id = EventIdArray[position];
                String eventName = EventNameArray[position];

                intents.putExtra("USERNAME",username);
                intents.putExtra("EVENT ID",Id);
                intents.putExtra("EVENT NAME",eventName);
                intents.putExtra("GUEST ID",guestID);
                intents.putExtra("GUEST NAME",guestName);
                startActivity(intents);
            }
        });
    }
}
