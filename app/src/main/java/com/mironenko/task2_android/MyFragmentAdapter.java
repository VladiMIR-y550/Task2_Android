package com.mironenko.task2_android;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentAdapter extends FragmentStateAdapter {
    public final String LOG_TAG = "My Fragment Adapter";
    private List<Fragment> fragmentList;

    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.fragmentList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        if (position == 1) {
//            Log.d(LOG_TAG, "Map fragment CREATED!");
////            return MapFragment.newInstance(handler);
//        }
//        Log.d(LOG_TAG, "Collection fragment CREATED!");
//        return CollectionFragment.newInstance(handler);
//    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public void addFragment(Fragment fragmentNewInstance) {
        fragmentList.add(fragmentNewInstance);
    }
}
