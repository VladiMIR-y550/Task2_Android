package com.mironenko.task2_android.Model;

import android.os.Handler;
import android.os.Message;

import com.mironenko.task2_android.Presenter.MyJob;
import com.mironenko.task2_android.Presenter.NamesCollections;
import com.mironenko.task2_android.Presenter.TasksList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListLab {
    private final String LOG_TAG = "ListLab log";

    private final int MSG_INITIAL_BASIC_COLLECTION = 2;

    private final String NEW_MY_COLLECTION = "myCollection";

    private final String NEW_MY_MAP = "myMap";

    private final String collectionSize;

    private static ListLab listLab;

    private List<Integer> basicList = new ArrayList<>();

    private final Map<String, Integer> basicMap = new HashMap<>();

    private final List<MyList> myLists;

    private final List<MyList> myMaps;

    private Handler handler;

    private ListLab(Handler handler, String collectionSize) {
        this.handler = handler;
        this.collectionSize = collectionSize;
        myLists = new ArrayList<>();
        myMaps = new ArrayList<>();
        instanceCollection(NEW_MY_COLLECTION);
//        instanceCollection(NEW_MY_MAP);
    }

    public static ListLab getListLab(Handler handler, String collectionSize) {
        if (listLab == null) {
            listLab = new ListLab(handler, collectionSize);
        }
        return listLab;
    }

    public List<MyList> getMyCollections() {
        return myLists;
    }

    public List<MyList> getMyMaps() {
        return myMaps;
    }

    private void instanceCollection(String newObject) {
        switch (newObject) {
            case NEW_MY_COLLECTION:
                initialList();
                fillObjectsData(myLists);
//            case NEW_MY_MAP:
//                initialMap();
//                fillObjectsData(myMaps);
        }
    }

    private void fillObjectsData(List<MyList> myList) {
        for (TasksList task : TasksList.values()) {
            for (NamesCollections name : NamesCollections.values()) {
                MyList myCollection = new MyList(name, task);
                myList.add(myCollection);
            }
        }
    }

    private void initialList() {
        new Thread(new Runnable() {
            Message msg;

            @Override
            public void run() {
                int size = Integer.parseInt(collectionSize);
                Integer[] array = new Integer[size];
                Arrays.fill(array, 1);
                basicList = Arrays.asList(array);
                msg = Message.obtain(handler, MSG_INITIAL_BASIC_COLLECTION);
                handler.sendMessage(msg);
            }
        }).start();
    }

    public void startJob() {
        for (int i = 0; i < myLists.size(); i++) {
            MyJob myJob = new MyJob(handler);
            myJob.setMyList(myLists.get(i));
            myJob.setBasicList(basicList);
            myJob.start();
        }
    }
}
