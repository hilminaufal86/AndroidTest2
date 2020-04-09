package com.example.app_test1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

public class GuestFragment extends Fragment implements LoaderManager.LoaderCallbacks<GuestStruct> {

    private GuestStruct guestStruct;
    private int page;
    private int perPage;
    private GuestAdapter guestAdapter;
    private Context context;

    public GuestFragment() {

    }

    public static GuestFragment newInstance(int page, int perPage) {
        GuestFragment fragment = new GuestFragment();
        Bundle args = new Bundle();
        args.putInt("page",page);
        args.putInt("perPage",perPage);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt("page");
            perPage = getArguments().getInt("perPage");
        }

        getLoaderManager().initLoader(0,null,this).forceLoad();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.guest_view_list,container,false);

        GridView gridView = view.findViewById(R.id.gridview);

        //guestAdapter = new GuestAdapter(getParentFragment().getContext(),guestStruct);
        guestAdapter = new GuestAdapter(getActivity().getApplicationContext(),guestStruct);

        gridView.setAdapter(guestAdapter);

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Loader<GuestStruct> onCreateLoader(int id, @Nullable Bundle args) {
        return new GuestLoader(getActivity().getApplicationContext(),page+1,perPage);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<GuestStruct> loader, GuestStruct data) {
        guestStruct = data;

        if (guestAdapter != null) {
            guestAdapter.setGuestStruct(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<GuestStruct> loader) {

    }

    public void reloadFragment() {
        getLoaderManager().restartLoader(getId(), null, this).forceLoad();
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

}
