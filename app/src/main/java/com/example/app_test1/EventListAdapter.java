package com.example.app_test1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.icu.text.Transliterator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class EventListAdapter extends ArrayAdapter {

    private final Activity context;

    private final Integer[] eventIdArray;
    private final Integer[] eventImageArray;
    private final String[] eventNameArray;
    private final String[] eventDateArray;

    public EventListAdapter(Activity context,Integer[] eventIdParam, String[] eventNameParam, Integer[] eventImageParam, String[] eventDateParam) {

        super(context,R.layout.event_row,eventIdParam);
        this.context = context;
        this.eventIdArray = eventIdParam;
        this.eventImageArray = eventImageParam;
        this.eventNameArray = eventNameParam;
        this.eventDateArray = eventDateParam;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View eventView = inflater.inflate(R.layout.event_row, null,true);

        TextView idField = eventView.findViewById(R.id.idEvent);
        TextView nameField = (TextView) eventView.findViewById(R.id.nameEvent);
        TextView dateField = (TextView) eventView.findViewById(R.id.dateEvent);
        ImageView imageField = (ImageView) eventView.findViewById(R.id.imageEvent);

        Log.d(eventIdArray[position].toString(), "ID::");
        idField.setText(eventIdArray[position].toString());
        nameField.setText(eventNameArray[position]);
        dateField.setText(eventDateArray[position]);
        imageField.setImageResource(eventImageArray[position]);

        return eventView;
        //return super.getView(position, convertView, parent);
    }
}
