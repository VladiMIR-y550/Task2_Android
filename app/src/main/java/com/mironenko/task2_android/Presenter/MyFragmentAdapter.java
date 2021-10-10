package com.mironenko.task2_android.Presenter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentAdapter extends FragmentStateAdapter {
    public static final String LOG_TAG = "My Fragment Adapter";
    Bundle bundle;

    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Bundle bundleFragment) {
        super(fragmentManager, lifecycle);
        this.bundle = bundleFragment;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            Log.d(LOG_TAG, "Map fragment CREATED!");
            return MapFragment.newInstance(bundle);
        }
        Log.d(LOG_TAG, "Collection fragment CREATED!");
        return CollectionFragment.newInstance(bundle);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
