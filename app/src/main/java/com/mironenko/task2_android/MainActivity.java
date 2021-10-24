package com.mironenko.task2_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mironenko.task2_android.MyFragmentAdapter;
import com.mironenko.task2_android.databinding.ActivityMainBinding;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        TabLayout.OnTabSelectedListener,
        TextView.OnEditorActionListener {

    public final String LOG_TAG = "myLog Activity";
    public final String CURRENT_POSITION = "currentPosition";
    public final String COLLECTION_SIZE = "collectionSize";
    private String collectionSize;
    private ActivityMainBinding binding;
    private TextInputEditText input;
    private Button btn_calculate;
    private MyFragmentAdapter adapter;
    private ViewPager2 pager2;
    private TabLayout tabLayout;
    private final Bundle bundleFragment = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        btn_calculate = binding.includeInputLayout.btnCalculate;
        tabLayout = binding.tabLayout;
        pager2 = binding.viewPager2;
        input = binding.includeInputLayout.texInputET;
        tabLayout.addOnTabSelectedListener(this);
        btn_calculate.setOnClickListener(this);
        input.setOnEditorActionListener(this);


        FragmentManager fm = getSupportFragmentManager();

        if (savedInstanceState != null) {
            inputScreenGone();
            pager2.setCurrentItem(savedInstanceState.getInt(CURRENT_POSITION));
        }
        adapter = new MyFragmentAdapter(fm, getLifecycle(), bundleFragment);
        pager2.setAdapter(adapter);
        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, "Button clicked");
        inputProcessing(Objects.requireNonNull(binding.includeInputLayout.texInputET.getText()).toString());
        inputScreenGone();
    }

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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceSave) {
        super.onSaveInstanceState(savedInstanceSave);
        int currentItem = pager2.getCurrentItem();
        savedInstanceSave.putInt(CURRENT_POSITION, currentItem);
    }

    private void inputProcessing(String inputString) {
        collectionSize = String.valueOf(inputString);
        bundleFragment.putString(COLLECTION_SIZE, collectionSize);
        inputScreenGone();
    }

    private void inputScreenGone() {
        binding.includeInputLayout.layoutInput.setVisibility(View.GONE);
        pager2.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        inputProcessing(Objects.requireNonNull(binding.includeInputLayout.texInputET.getText()).toString());
        return false;
    }
}