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

    private static final String KEY_PROGRESS_VISIBLE = "progressVisible";
    public final String LOG_TAG = "myLog Activity";
    public final String CURRENT_POSITION = "currentPosition";
    public static final String COLLECTION_SIZE = "collectionSize";
    public static final int MSG_INITIAL_BASIC_COLLECTION = 0;
    private static final int MSG_SHOW_RESULT = 1;
    private int collectionSize;
    private ActivityMainBinding binding;
    private MyFragmentAdapter adapter;
    private final Bundle bundleFragment = new Bundle();
    private InitialBaseCell initialBaseCell;
    private MainState mainState;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MSG_INITIAL_BASIC_COLLECTION:
                    showProgress(false);
                    inputScreenGone();
                    return true;
                case MSG_SHOW_RESULT:
                    DataCell dataCell = (DataCell) msg.obj;
                    CollectionFragment fragment = FragmentManager.findFragment(findViewById(R.id.fragment_collection));
                    CellView cellView = fragment.getCellViewMap().get(dataCell.getViewKey());
                    if (cellView != null) {
                        cellView.showResult(dataCell);
                    }
                    return true;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mainState = StateManager.getState(this, new MainState());
        binding.tabLayout.addOnTabSelectedListener(this);
        binding.includeInputLayout.btnCalculate.setOnClickListener(this);
        binding.includeInputLayout.texInputET.setOnEditorActionListener(this);

        if (savedInstanceState != null) {
            binding.viewPager2.setCurrentItem(savedInstanceState.getInt(CURRENT_POSITION));
            handler = mainState.mainHandler;
            int progressVisible = savedInstanceState.getInt(KEY_PROGRESS_VISIBLE);
            if (progressVisible == View.VISIBLE) {
                binding.pbCalculate.progressLayout.setVisibility(progressVisible);
            }
//            binding.pbCalculate.progressLayout.setVisibility(savedInstanceState.getInt(KEY_PROGRESS_VISIBLE));
        } else {

        }


        FragmentManager fm = getSupportFragmentManager();

        bundleFragment.putInt(COLLECTION_SIZE, collectionSize);

        adapter = new MyFragmentAdapter(fm, getLifecycle(), bundleFragment, handler);
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
        showProgress(true);
        initialBaseCell = InitialBaseCell.getInstance(collectionSize, handler);
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
//        binding = null;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceSave) {
        super.onSaveInstanceState(savedInstanceSave);
//        int currentItem = binding.viewPager2.getCurrentItem();
//        savedInstanceSave.putInt(CURRENT_POSITION, currentItem);
        mainState.mainHandler = handler;

//        int currentItem = binding.viewPager2.getCurrentItem();
        mainState.currentItem = binding.viewPager2.getCurrentItem();
        int progressVisible = binding.pbCalculate.progressLayout.getVisibility();
        mainState.progressVisible = binding.pbCalculate.progressLayout.getVisibility();

        savedInstanceSave.putInt(CURRENT_POSITION, mainState.currentItem);
        savedInstanceSave.putInt(KEY_PROGRESS_VISIBLE,mainState.progressVisible);
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


    /**
     * - инициализируем базовую коллекцию в MainActivity, показываем прогресс общий
     * - передаём базовую коллекцию во фрагменты
     * - в фрагментах происходит наполнение базы List-ов или Map-ов:
     * - в каждом объекте ячейки с данными инициализируеться коллекция для выполнения задания,
     * заполняеться значениями из базовой коллекции
     * - добавляеться КЛЮЧ по которому мы найдём ячейку
     * - добавляется идентификатор Task, дял выполнения конкретных вычислений
     *
     * - После этого можно убрать прогресс общий и layout_input, и показать viewPager с фрагментами
     * - Стартуем все вычисления
     */
}