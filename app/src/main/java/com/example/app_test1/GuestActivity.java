package com.example.app_test1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class GuestActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<GuestStruct> {

    int page = 0;
    int perPage = 5;
    int total;
    int totalPage = 10;
    String username;
    int eventID;
    String eventName;
    GuestStruct guestStruct;
    GridView gridView;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_four);
/*
        if (savedInstanceState == null) {
            GuestFragment guestFragment = GuestFragment.newInstance(0,6);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.swlayout,guestFragment,"GUEST FRAGMENT").commit();
            //SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        }
*/
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swlayout);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark),getResources().getColor(R.color.colorPrimary));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Handler untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {


                        //GuestFragment guestFragment = (GuestFragment) getSupportFragmentManager().findFragmentByTag("GUEST FRAGMENT");

                        //if (guestFragment != null) {
                        //    guestFragment.reloadFragment();
                        //}
                        // Berhenti berputar/refreshing

                        Intent intents = new Intent(GuestActivity.this,GuestActivity.class);

                        intents.putExtra("USERNAME",username);
                        intents.putExtra("USERNAME",username);
                        //intents.putExtra("GUEST ID",Id);
                        //intents.putExtra("GUEST NAME",Name);
                        intents.putExtra("EVENT ID",eventID);
                        intents.putExtra("EVENT NAME",eventName);
                        mSwipeRefreshLayout.setRefreshing(false);
                        startActivity(intents);
                        // fungsi-fungsi lain yang dijalankan saat refresh berhenti

                    }
                }, 5000);
            }
        });


        Intent intent = getIntent();
        if (intent.getStringExtra("USERNAME") != null) {
            username = intent.getStringExtra("USERNAME");
            eventID = intent.getIntExtra("EVENT ID",-1);
            eventName = intent.getStringExtra("EVENT NAME");
        }
        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            gridView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY-oldScrollY > 5) {
                         if (page+1 <= totalPage) {
                            return new GuestLoader(this,page+1,perPage);
                        }
                        else {
                            return new GuestLoader(this,page,perPage);
                        }
                    }
                }
            });
        }

         */
        getSupportLoaderManager().initLoader(0, null, this).forceLoad();
    }


    @NonNull
    @Override
    public Loader<GuestStruct> onCreateLoader(int id, @Nullable Bundle args) {

        if (page+1 <= totalPage) {
            return new GuestLoader(this,page+1,perPage);
        }
        else {
            return new GuestLoader(this,page,perPage);
        }

    }

    @Override
    public void onLoadFinished(@NonNull Loader<GuestStruct> loader, GuestStruct data) {
        guestStruct = data;
        page = guestStruct.getCurPage();
        totalPage = guestStruct.getTotalPage();
        total = guestStruct.getTotal();

        Log.d(guestStruct.getGuests(0).getFirstName(), "first name");
        GuestAdapter guestAdapter = new GuestAdapter(this,guestStruct);
        gridView = findViewById(R.id.gridview);
        gridView.setAdapter(guestAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intents = new Intent(GuestActivity.this, MainActivity.class);
                Integer Id = guestStruct.getGuests(position).getAttributeID();
                String Name = guestStruct.getGuests(position).getFirstName()+' '+ guestStruct.getGuests(position).getLastName();

                intents.putExtra("USERNAME",username);
                intents.putExtra("GUEST ID",Id);
                intents.putExtra("GUEST NAME",Name);
                intents.putExtra("EVENT ID",eventID);
                intents.putExtra("EVENT NAME",eventName);
                intents.putExtra("FROM","GUEST");
                startActivity(intents);
            }
        });
    }

    @Override
    public void onLoaderReset(@NonNull Loader<GuestStruct> loader) {

    }

    public void refreshData() {

    }
}
