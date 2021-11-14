package com.mironenko.task2_android;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.mironenko.task2_android.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        TabLayout.OnTabSelectedListener,
        TextView.OnEditorActionListener {

    public final String LOG_TAG = "myLog Activity";
    public final String CURRENT_POSITION = "currentPosition";
    public static final String COLLECTION_SIZE = "collectionSize";
    public static final int MSG_INITIAL_BASIC_COLLECTION = 0;
    private static final String KEY_INPUT_LAYOUT_VISIBLE = "inputLayoutVisible";
    private static final String KEY_VIEW_PAGER_VISIBLE = "viewPagerVisible";
    private static final String KEY_PROGRESS_VISIBLE = "progressVisible";
    private int collectionSize;
    private ActivityMainBinding binding;
    private MyFragmentAdapter adapter;
    private final Bundle bundleFragment = new Bundle();
    private InitialBaseCell initialBaseCell;
    private Handler mainHandler;
    private CollectionFragment collectionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        collectionFragment = CollectionFragment.newInstance();
        initialBaseCell = InitialBaseCell.getInstance();
        mainHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if (msg.what == MSG_INITIAL_BASIC_COLLECTION) {
                    showProgress(false);
                    inputScreenGone();
                    return true;
                }
                return false;
            }
        });
        initialBaseCell.setHandler(mainHandler);

        binding.tabLayout.addOnTabSelectedListener(this);
        binding.includeInputLayout.btnCalculate.setOnClickListener(this);
        binding.includeInputLayout.texInputET.setOnEditorActionListener(this);

        if (savedInstanceState != null) {
            restoreBundle(savedInstanceState);
        }
        FragmentManager fm = getSupportFragmentManager();
        bundleFragment.putInt(COLLECTION_SIZE, collectionSize);

        adapter = new MyFragmentAdapter(fm, getLifecycle());
        adapter.addFragment(collectionFragment);

        binding.viewPager2.setAdapter(adapter);
        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, "Button clicked");
        getCollectionSizeFromInput();
        initialBaseCell.setCollectionSize(collectionSize);
        showProgress(true);
        initialBaseCell.initialBasicList();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        binding.viewPager2.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        getCollectionSizeFromInput();
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        mainHandler = null;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceSave) {
        super.onSaveInstanceState(savedInstanceSave);

        savedInstanceSave.putInt(CURRENT_POSITION, binding.viewPager2.getCurrentItem());
        savedInstanceSave.putInt(KEY_PROGRESS_VISIBLE, binding.pbCalculate.progressLayout.getVisibility());
        savedInstanceSave.putInt(KEY_INPUT_LAYOUT_VISIBLE, binding.includeInputLayout.layoutInput.getVisibility());
        savedInstanceSave.putInt(KEY_VIEW_PAGER_VISIBLE, binding.viewPager2.getVisibility());
    }


    private void getCollectionSizeFromInput() {
        collectionSize = Integer.parseInt(Objects.requireNonNull(binding.includeInputLayout.texInputET.getText()).toString());
    }

    private void showProgress(boolean visible) {
        if (visible) {
            binding.pbCalculate.progressLayout.setVisibility(View.VISIBLE);
        } else {
            binding.pbCalculate.progressLayout.setVisibility(View.GONE);
        }
    }

    private void inputScreenGone() {
        binding.includeInputLayout.layoutInput.setVisibility(View.GONE);
        binding.viewPager2.setVisibility(View.VISIBLE);
    }

    private void restoreBundle(Bundle savedInstanceState) {
        binding.viewPager2.setCurrentItem(savedInstanceState.getInt(CURRENT_POSITION));
        if (savedInstanceState.getInt(KEY_PROGRESS_VISIBLE) == View.VISIBLE) {
            binding.pbCalculate.progressLayout.setVisibility(savedInstanceState.getInt(KEY_PROGRESS_VISIBLE));
        } else if (savedInstanceState.getInt(KEY_INPUT_LAYOUT_VISIBLE) != View.VISIBLE) {
            showProgress(false);
            inputScreenGone();
        }
    }
}