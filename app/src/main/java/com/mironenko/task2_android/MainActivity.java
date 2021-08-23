package com.mironenko.task2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    public final String LOG_TAG = "myTag";

    private int collectionSize;

    ViewGroup inputSize;
    EditText editText;
    Button button;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "create Activity");
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager_2);
        inputSize = (ViewGroup) findViewById(R.id.layout_input);
        button = inputSize.findViewById(R.id.btn_Calculate);
        editText = inputSize.findViewById(R.id.et_CollectionSize);

        if (savedInstanceState != null) {
            pager2.setAdapter(adapter);
            pager2.setCurrentItem(savedInstanceState.getInt("currentPosition"));
        }

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);
        inputSize.setVisibility(View.VISIBLE);
        button.setOnClickListener(this);
        editText.setOnEditorActionListener(this);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public void onClick(View v) {
        String input = editText.getText().toString();
        collectionSize = Integer.parseInt(input);
        inputScreenGone();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        String input = editText.getText().toString();
        collectionSize = Integer.parseInt(input);
        inputScreenGone();
        return false;
    }

    class FragmentAdapter extends FragmentStateAdapter {

        public FragmentAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @org.jetbrains.annotations.NotNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 1) {
                return MapFragment.newInstance(collectionSize);
            }
            return CollectionFragment.newInstance(collectionSize);
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

    private void inputScreenGone() {
        pager2.setVisibility(View.VISIBLE);
        inputSize.setVisibility(View.GONE);
    }

    @Override
    protected void onSaveInstanceState(@NonNull @NotNull Bundle savedInstanceSave) {
        super.onSaveInstanceState(savedInstanceSave);
        int currentItem = pager2.getCurrentItem();
        savedInstanceSave.putInt("currentPosition", currentItem);
    }
}