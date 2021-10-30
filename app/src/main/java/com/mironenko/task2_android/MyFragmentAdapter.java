package com.mironenko.task2_android;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentAdapter extends FragmentStateAdapter {
    public final String LOG_TAG = "My Fragment Adapter";
    private final Bundle bundle;
    private final Handler handler;

    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Bundle bundle, Handler handler) {
        super(fragmentManager, lifecycle);
        this.bundle = bundle;
        this.handler = handler;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            Log.d(LOG_TAG, "Map fragment CREATED!");
//            return MapFragment.newInstance(handler);
        }
        Log.d(LOG_TAG, "Collection fragment CREATED!");
        return CollectionFragment.newInstance(bundle, handler);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
