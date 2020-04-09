package com.example.app_test1;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class GuestLoader extends AsyncTaskLoader<GuestStruct> {
    int page;
    int per_page;

    public GuestLoader(@NonNull Context context) {
        super(context);
        this.page = 1;
        this.per_page = 6;
    }

    public GuestLoader(@NonNull Context context, int page) {
        super(context);
        this.page = page;
        this.per_page = 6;
    }

    public GuestLoader(@NonNull Context context, int page, int per_page) {
        super(context);
        this.page = page;
        this.per_page = per_page;
    }

    @Nullable
    @Override
    public GuestStruct loadInBackground() {
        return NetworkUtils.getGuest(page,per_page);
    }
}
