package com.example.app_test1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    private String UserName;
    private TextView NameView;

    private Integer EventID;
    private String EventName;
    private Button EventButton;
    private Boolean fromEvent;

    private Integer GuestID;
    private String GuestName;
    private Button GuestButton;
    private Boolean fromGuest;

    public MainActivity() {
        EventID = -1;
        EventName = "Choose Event";
        GuestID = -1;
        GuestName = "Choose Guest";
        fromEvent = false;
        fromGuest = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_two);

        Intent intent = getIntent();
        if (intent.getStringExtra("USERNAME")!="") {
            UserName = intent.getStringExtra("USERNAME");
        }

        NameView = findViewById(R.id.nama);
        EventButton = findViewById(R.id.Event);
        GuestButton = findViewById(R.id.Guest);

        NameView.setText(UserName);
        if (GuestID==-1) {
            GuestButton.setText(GuestName);

        }
        if (EventID==-1) {
            EventButton.setText(EventName);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();

        Integer tempEventId = intent.getIntExtra("EVENT ID",-1);
        String from = "this";
        if (tempEventId != -1) {
            EventID = tempEventId;
            EventName = intent.getStringExtra("EVENT NAME");

            EventButton.setText(EventName);
            EventButton.setBackground(getDrawable(R.drawable.btn_signup_selected));
        } else {
            EventButton.setText(EventName);
        }

        Integer tempGuestId = intent.getIntExtra("GUEST ID",-1);
        Boolean isDifferent = false;
        if (tempGuestId!=-1) {
            GuestID = tempGuestId;
            if (!GuestName.equals(intent.getStringExtra("GUEST NAME"))) {
                isDifferent = true;
            }
            GuestName = intent.getStringExtra("GUEST NAME");
            if (intent.getStringExtra("FROM")!=null) {
                from = intent.getStringExtra("FROM");
            }

            GuestButton.setText(GuestName);
            GuestButton.setBackground(getDrawable(R.drawable.btn_signup_selected));
        } else {
            GuestButton.setText(GuestName);
        }

        Log.d(from, "FROM: ");
        if (from.equals("GUEST")) {
            if ((GuestID%2==0 || GuestID%3==0) && isDifferent ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                if (GuestID%6==0) {
                    builder.setMessage("iOS");

                } else if (GuestID%3==0) {
                    builder.setMessage("Android");

                } else {
                    builder.setMessage("Blackberry");

                }

                builder.setNeutralButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

    }

    public void onClickEvent(View v) {
        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra("USERNAME",UserName);
        intent.putExtra("GUEST ID",GuestID);
        intent.putExtra("GUEST NAME",GuestName);

        startActivity(intent);
    }

    public  void onClickGuest(View v) {
        Intent intent = new Intent(this, GuestActivity.class);
        intent.putExtra("USERNAME",UserName);
        intent.putExtra("EVENT ID",EventID);
        intent.putExtra("EVENT NAME",EventName);
        startActivity(intent);
    }
}
