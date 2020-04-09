package com.example.app_test1;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GuestAdapter extends BaseAdapter {

    private GuestStruct guest;
    private final Context context;

    public GuestAdapter(Context context,GuestStruct guests) {
        this.guest = guests;
        this.context = context;
    }

    public void setGuestStruct(GuestStruct guest) {
        this.guest = guest;
    }

    @Override
    public int getCount() {
        return guest.getGuestList().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Guest g = guest.getGuests(position);

        //if (convertView==null) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.guest_layout,null);


        ImageView avatar = convertView.findViewById(R.id.avatar);
        TextView name = convertView.findViewById(R.id.guest_name);
        //TextView email = convertView.findViewById(R.id.guest_email);

        name.setText(g.getFirstName()+" "+g.getLastName());
        //avatar.setImageResource(R.drawable.event4);
        CircularProgressDrawable circular = new CircularProgressDrawable(context);
        circular.start();
        Glide.with(context)
                .load(g.getAvatar())
                .error(R.mipmap.android)
                .override(400,400)
                .placeholder(circular)
                .into(avatar);

        return convertView;
    }
}
