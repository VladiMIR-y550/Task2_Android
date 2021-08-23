package com.mironenko.task2_android;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CollectionFragment extends Fragment {

    public final String LOG_TAG = "myTag";

    final int ADDING_IN_THE_BEGINNING_ARRAY_LIST = 0;
    final int ADDING_IN_THE_BEGINNING_LINKED_LIST = 1;
    final int ADDING_IN_THE_BEGINNING_COPY_ON_WRITE_ARRAY_LIST = 2;
    final int ADDING_IN_THE_MIDDLE_ARRAY_LIST = 3;
    final int ADDING_IN_THE_MIDDLE_LINKED_LIST = 4;
    final int ADDING_IN_THE_MIDDLE_COPY_ON_WRITE_ARRAY_LIST = 5;
    final int ADDING_IN_THE_END_ARRAY_LIST = 6;
    final int ADDING_IN_THE_END_LINKED_LIST = 7;
    final int ADDING_IN_THE_END_COPY_ON_WRITE_ARRAY_LIST = 8;
    final int SEARCH_BY_VALUE_ARRAY_LIST = 9;
    final int SEARCH_BY_VALUE_LINKED_LIST = 10;
    final int SEARCH_BY_VALUE_COPY_ON_WRITE_ARRAY_LIST = 11;
    final int REMOVING_IN_THE_BEGINNING_ARRAY_LIST = 12;
    final int REMOVING_IN_THE_BEGINNING_LINKED_LIST = 13;
    final int REMOVING_IN_THE_BEGINNING_COPY_ON_WRITE_ARRAY_LIST = 14;
    final int REMOVING_IN_THE_MIDDLE_ARRAY_LIST = 15;
    final int REMOVING_IN_THE_MIDDLE_LINKED_LIST = 16;
    final int REMOVING_IN_THE_MIDDLE_COPY_ON_WRITE_ARRAY_LIST = 17;
    final int REMOVING_IN_THE_END_ARRAY_LIST = 18;
    final int REMOVING_IN_THE_END_LINKED_LIST = 19;
    final int REMOVING_IN_THE_END_COPY_ON_WRITE_ARRAY_LIST = 20;

    private TextView tv_addBeginArrayList;
    private ProgressBar pb_addBeginArrayList;
    private TextView tv_addBeginLinkedList;
    private ProgressBar pb_addBeginLinkedList;
    private TextView tv_addBeginCopyOnWriteArrayList;
    private ProgressBar pb_addBeginCopyOnWriteArrayList;

    private TextView tv_addMiddleArrayList;
    private ProgressBar pb_addMiddleArrayList;
    private TextView tv_addMiddleLinkedList;
    private ProgressBar pb_addMiddleLinkedList;
    private TextView tv_addMiddleCopyOnWriteArrayList;
    private ProgressBar pb_addMiddleCopyOnWriteArrayList;

    private TextView tv_addEndArrayList;
    private ProgressBar pb_addEndArrayList;
    private TextView tv_addEndLinkedList;
    private ProgressBar pb_addEndLinkedList;
    private TextView tv_addEndCopyOnWriteArrayList;
    private ProgressBar pb_addEndCopyOnWriteArrayList;

    private TextView tv_searchArrayList;
    private ProgressBar pb_searchArrayList;
    private TextView tv_searchLinkedList;
    private ProgressBar pb_searchLinkedList;
    private TextView tv_searchCopyOnWriteArrayList;
    private ProgressBar pb_searchCopyOnWriteArrayList;

    private TextView tv_removingBeginArrayList;
    private ProgressBar pb_removingBeginArrayList;
    private TextView tv_removingBeginLinkedList;
    private ProgressBar pb_removingBeginLinkedList;
    private TextView tv_removingBeginCopyOnWriteArrayList;
    private ProgressBar pb_removingBeginCopyOnWriteArrayList;

    private TextView tv_removingMiddleArrayList;
    private ProgressBar pb_removingMiddleArrayList;
    private TextView tv_removingMiddleLinkedList;
    private ProgressBar pb_removingMiddleLinkedList;
    private TextView tv_removingMiddleCopyOnWriteArrayList;
    private ProgressBar pb_removingMiddleCopyOnWriteArrayList;

    private TextView tv_removingEndArrayList;
    private ProgressBar pb_removingEndArrayList;
    private TextView tv_removingEndLinkedList;
    private ProgressBar pb_removingEndLinkedList;
    private TextView tv_removingEndCopyOnWriteArrayList;
    private ProgressBar pb_removingEndCopyOnWriteArrayList;

    private static final String ARG_COLLECTION_SIZE = "collectionSize";
    private int size;
    private final List<Integer> basicList = new ArrayList<>();

    public CollectionFragment(int size) {
        this.size = size;
    }


    public static CollectionFragment newInstance(int size) {
        CollectionFragment fragment = new CollectionFragment(size);
        Bundle args = new Bundle();
        args.putInt(ARG_COLLECTION_SIZE, size);
        fragment.setArguments(args);
        return fragment;
    }

    final Handler handlerCollection = new Handler(Looper.getMainLooper()) {
        @SuppressLint({"ShowToast", "SetTextI18n"})
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ADDING_IN_THE_BEGINNING_ARRAY_LIST:
                    pb_addBeginArrayList.setVisibility(View.INVISIBLE);
                    tv_addBeginArrayList.setText("" + msg.obj);
                    break;
                case ADDING_IN_THE_BEGINNING_LINKED_LIST:
                    pb_addBeginLinkedList.setVisibility(View.INVISIBLE);
                    tv_addBeginLinkedList.setText("" + msg.obj);
                    break;
                case ADDING_IN_THE_BEGINNING_COPY_ON_WRITE_ARRAY_LIST:
                    pb_addBeginCopyOnWriteArrayList.setVisibility(View.INVISIBLE);
                    tv_addBeginCopyOnWriteArrayList.setText("" + msg.obj);
                    break;
                case ADDING_IN_THE_MIDDLE_ARRAY_LIST:
                    pb_addMiddleArrayList.setVisibility(View.INVISIBLE);
                    tv_addMiddleArrayList.setText("" + msg.obj);
                    break;
                case ADDING_IN_THE_MIDDLE_LINKED_LIST:
                    pb_addMiddleLinkedList.setVisibility(View.INVISIBLE);
                    tv_addMiddleLinkedList.setText("" + msg.obj);
                    break;
                case ADDING_IN_THE_MIDDLE_COPY_ON_WRITE_ARRAY_LIST:
                    pb_addMiddleCopyOnWriteArrayList.setVisibility(View.INVISIBLE);
                    tv_addMiddleCopyOnWriteArrayList.setText("" + msg.obj);
                    break;
                case ADDING_IN_THE_END_ARRAY_LIST:
                    pb_addEndArrayList.setVisibility(View.INVISIBLE);
                    tv_addEndArrayList.setText("" + msg.obj);
                    break;
                case ADDING_IN_THE_END_LINKED_LIST:
                    pb_addEndLinkedList.setVisibility(View.INVISIBLE);
                    tv_addEndLinkedList.setText("" + msg.obj);
                    break;
                case ADDING_IN_THE_END_COPY_ON_WRITE_ARRAY_LIST:
                    pb_addEndCopyOnWriteArrayList.setVisibility(View.INVISIBLE);
                    tv_addEndCopyOnWriteArrayList.setText("" + msg.obj);
                    break;
                case SEARCH_BY_VALUE_ARRAY_LIST:
                    pb_searchArrayList.setVisibility(View.INVISIBLE);
                    tv_searchArrayList.setText("" + msg.obj);
                    break;
                case SEARCH_BY_VALUE_LINKED_LIST:
                    pb_searchLinkedList.setVisibility(View.INVISIBLE);
                    tv_searchLinkedList.setText("" + msg.obj);
                    break;
                case SEARCH_BY_VALUE_COPY_ON_WRITE_ARRAY_LIST:
                    pb_searchCopyOnWriteArrayList.setVisibility(View.INVISIBLE);
                    tv_searchCopyOnWriteArrayList.setText("" + msg.obj);
                    break;
                case REMOVING_IN_THE_BEGINNING_ARRAY_LIST:
                    pb_removingBeginArrayList.setVisibility(View.INVISIBLE);
                    tv_removingBeginArrayList.setText("" + msg.obj);
                    break;
                case REMOVING_IN_THE_BEGINNING_LINKED_LIST:
                    pb_removingBeginLinkedList.setVisibility(View.INVISIBLE);
                    tv_removingBeginLinkedList.setText("" + msg.obj);
                    break;
                case REMOVING_IN_THE_BEGINNING_COPY_ON_WRITE_ARRAY_LIST:
                    pb_removingBeginCopyOnWriteArrayList.setVisibility(View.INVISIBLE);
                    tv_removingBeginCopyOnWriteArrayList.setText("" + msg.obj);
                    break;
                case REMOVING_IN_THE_MIDDLE_ARRAY_LIST:
                    pb_removingMiddleArrayList.setVisibility(View.INVISIBLE);
                    tv_removingMiddleArrayList.setText("" + msg.obj);
                    break;
                case REMOVING_IN_THE_MIDDLE_LINKED_LIST:
                    pb_removingMiddleLinkedList.setVisibility(View.INVISIBLE);
                    tv_removingMiddleLinkedList.setText("" + msg.obj);
                    break;
                case REMOVING_IN_THE_MIDDLE_COPY_ON_WRITE_ARRAY_LIST:
                    pb_removingMiddleCopyOnWriteArrayList.setVisibility(View.INVISIBLE);
                    tv_removingMiddleCopyOnWriteArrayList.setText("" + msg.obj);
                    break;
                case REMOVING_IN_THE_END_ARRAY_LIST:
                    pb_removingEndArrayList.setVisibility(View.INVISIBLE);
                    tv_removingEndArrayList.setText("" + msg.obj);
                    break;
                case REMOVING_IN_THE_END_LINKED_LIST:
                    pb_removingEndLinkedList.setVisibility(View.INVISIBLE);
                    tv_removingEndLinkedList.setText("" + msg.obj);
                    break;
                case REMOVING_IN_THE_END_COPY_ON_WRITE_ARRAY_LIST:
                    pb_removingEndCopyOnWriteArrayList.setVisibility(View.INVISIBLE);
                    tv_removingEndCopyOnWriteArrayList.setText("" + msg.obj);
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            size = getArguments().getInt(ARG_COLLECTION_SIZE);
        }
        createdBasicCollection(size);
        Log.d(LOG_TAG, "Лист создан " + basicList.size());
        startTask(ListName.ARRAY_LIST, TaskName.ADDING_IN_THE_BEGINNING, ADDING_IN_THE_BEGINNING_ARRAY_LIST);
        startTask(ListName.LINKED_LIST, TaskName.ADDING_IN_THE_BEGINNING, ADDING_IN_THE_BEGINNING_LINKED_LIST);
        startTask(ListName.COPY_ON_WRITE_LIST, TaskName.ADDING_IN_THE_BEGINNING, ADDING_IN_THE_BEGINNING_COPY_ON_WRITE_ARRAY_LIST);
        startTask(ListName.ARRAY_LIST, TaskName.ADDING_IN_THE_MIDDLE, ADDING_IN_THE_MIDDLE_ARRAY_LIST);
        startTask(ListName.LINKED_LIST, TaskName.ADDING_IN_THE_MIDDLE, ADDING_IN_THE_MIDDLE_LINKED_LIST);
        startTask(ListName.COPY_ON_WRITE_LIST, TaskName.ADDING_IN_THE_MIDDLE, ADDING_IN_THE_MIDDLE_COPY_ON_WRITE_ARRAY_LIST);
        startTask(ListName.ARRAY_LIST, TaskName.ADDING_IN_THE_END, ADDING_IN_THE_END_ARRAY_LIST);
        startTask(ListName.LINKED_LIST, TaskName.ADDING_IN_THE_END, ADDING_IN_THE_END_LINKED_LIST);
        startTask(ListName.COPY_ON_WRITE_LIST, TaskName.ADDING_IN_THE_END, ADDING_IN_THE_END_COPY_ON_WRITE_ARRAY_LIST);
        startTask(ListName.ARRAY_LIST, TaskName.SEARCH_BY_VALUE, SEARCH_BY_VALUE_ARRAY_LIST);
        startTask(ListName.LINKED_LIST, TaskName.SEARCH_BY_VALUE, SEARCH_BY_VALUE_LINKED_LIST);
        startTask(ListName.COPY_ON_WRITE_LIST, TaskName.SEARCH_BY_VALUE, SEARCH_BY_VALUE_COPY_ON_WRITE_ARRAY_LIST);
        startTask(ListName.ARRAY_LIST, TaskName.REMOVING_IN_THE_BEGINNING, REMOVING_IN_THE_BEGINNING_ARRAY_LIST);
        startTask(ListName.LINKED_LIST, TaskName.REMOVING_IN_THE_BEGINNING, REMOVING_IN_THE_BEGINNING_LINKED_LIST);
        startTask(ListName.COPY_ON_WRITE_LIST, TaskName.REMOVING_IN_THE_BEGINNING, REMOVING_IN_THE_BEGINNING_COPY_ON_WRITE_ARRAY_LIST);
        startTask(ListName.ARRAY_LIST, TaskName.REMOVING_IN_THE_MIDDLE, REMOVING_IN_THE_MIDDLE_ARRAY_LIST);
        startTask(ListName.LINKED_LIST, TaskName.REMOVING_IN_THE_MIDDLE, REMOVING_IN_THE_MIDDLE_LINKED_LIST);
        startTask(ListName.COPY_ON_WRITE_LIST, TaskName.REMOVING_IN_THE_MIDDLE, REMOVING_IN_THE_MIDDLE_COPY_ON_WRITE_ARRAY_LIST);
        startTask(ListName.ARRAY_LIST, TaskName.REMOVING_IN_THE_END, REMOVING_IN_THE_END_ARRAY_LIST);
        startTask(ListName.LINKED_LIST, TaskName.REMOVING_IN_THE_END, REMOVING_IN_THE_END_LINKED_LIST);
        startTask(ListName.COPY_ON_WRITE_LIST, TaskName.REMOVING_IN_THE_END, REMOVING_IN_THE_END_COPY_ON_WRITE_ARRAY_LIST);
        handlerCollection.removeCallbacksAndMessages(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_addBeginArrayList = view.findViewById(R.id.tv_addBeginArrayList);
        pb_addBeginArrayList = view.findViewById(R.id.pb_addBeginArrayList);
        tv_addBeginLinkedList = view.findViewById(R.id.tv_addBeginLinkedList);
        pb_addBeginLinkedList = view.findViewById(R.id.pb_addBeginLinkedList);
        tv_addBeginCopyOnWriteArrayList = view.findViewById(R.id.tv_addBeginCopyOnWriteArrayList);
        pb_addBeginCopyOnWriteArrayList = view.findViewById(R.id.pb_addBeginCopyOnWriteArrayList);

        tv_addMiddleArrayList = view.findViewById(R.id.tv_addMiddleArrayList);
        pb_addMiddleArrayList = view.findViewById(R.id.pb_addMiddleArrayList);
        tv_addMiddleLinkedList = view.findViewById(R.id.tv_addMiddleLinkedList);
        pb_addMiddleLinkedList = view.findViewById(R.id.pb_addMiddleLinkedList);
        tv_addMiddleCopyOnWriteArrayList = view.findViewById(R.id.tv_addMiddleCopyOnWriteArrayList);
        pb_addMiddleCopyOnWriteArrayList = view.findViewById(R.id.pb_addMiddleCopyOnWriteArrayList);

        tv_addEndArrayList = view.findViewById(R.id.tv_addEndArrayList);
        pb_addEndArrayList = view.findViewById(R.id.pb_addEndArrayList);
        tv_addEndLinkedList = view.findViewById(R.id.tv_addEndLinkedList);
        pb_addEndLinkedList = view.findViewById(R.id.pb_addEndLinkedList);
        tv_addEndCopyOnWriteArrayList = view.findViewById(R.id.tv_addEndCopyOnWriteArrayList);
        pb_addEndCopyOnWriteArrayList = view.findViewById(R.id.pb_addEndCopyOnWriteArrayList);

        tv_searchArrayList = view.findViewById(R.id.tv_searchArrayList);
        pb_searchArrayList = view.findViewById(R.id.pb_searchArrayList);
        tv_searchLinkedList = view.findViewById(R.id.tv_searchLinkedList);
        pb_searchLinkedList = view.findViewById(R.id.pb_searchLinkedList);
        tv_searchCopyOnWriteArrayList = view.findViewById(R.id.tv_searchCopyOnWriteArrayList);
        pb_searchCopyOnWriteArrayList = view.findViewById(R.id.pb_searchCopyOnWriteArrayList);

        tv_removingBeginArrayList = view.findViewById(R.id.tv_removingBeginArrayList);
        pb_removingBeginArrayList = view.findViewById(R.id.pb_removingBeginArrayList);
        tv_removingBeginLinkedList = view.findViewById(R.id.tv_removingBeginLinkedList);
        pb_removingBeginLinkedList = view.findViewById(R.id.pb_removingBeginLinkedList);
        tv_removingBeginCopyOnWriteArrayList = view.findViewById(R.id.tv_removingBeginCopyOnWriteArrayList);
        pb_removingBeginCopyOnWriteArrayList = view.findViewById(R.id.pb_removingBeginCopyOnWriteArrayList);

        tv_removingMiddleArrayList = view.findViewById(R.id.tv_removingMiddleArrayList);
        pb_removingMiddleArrayList = view.findViewById(R.id.pb_removingMiddleArrayList);
        tv_removingMiddleLinkedList = view.findViewById(R.id.tv_removingMiddleLinkedList);
        pb_removingMiddleLinkedList = view.findViewById(R.id.pb_removingMiddleLinkedList);
        tv_removingMiddleCopyOnWriteArrayList = view.findViewById(R.id.tv_removingMiddleCopyOnWriteArrayList);
        pb_removingMiddleCopyOnWriteArrayList = view.findViewById(R.id.pb_removingMiddleCopyOnWriteArrayList);

        tv_removingEndArrayList = view.findViewById(R.id.tv_removingEndArrayList);
        pb_removingEndArrayList = view.findViewById(R.id.pb_removingEndArrayList);
        tv_removingEndLinkedList = view.findViewById(R.id.tv_removingEndLinkedList);
        pb_removingEndLinkedList = view.findViewById(R.id.pb_removingEndLinkedList);
        tv_removingEndCopyOnWriteArrayList = view.findViewById(R.id.tv_removingEndCopyOnWriteArrayList);
        pb_removingEndCopyOnWriteArrayList = view.findViewById(R.id.pb_removingEndCopyOnWriteArrayList);
    }

    private void createdBasicCollection(int size) {
        for (int i = 0; i < size; i++) {
            basicList.add(0);
        }
    }

    private void startTask(ListName listName, TaskName taskName, int massage) {
        Thread thread = new Thread(new Runnable() {
            Message msg;
            Long result;
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                TaskCollection task = new TaskCollection();
                result = task.startTask(basicList, listName, taskName);
                msg = handlerCollection.obtainMessage(massage, 0, 0, result);
                handlerCollection.sendMessage(msg);
            }
        });
        thread.start();
    }
}