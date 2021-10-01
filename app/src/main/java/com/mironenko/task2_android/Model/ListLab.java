package com.mironenko.task2_android.Model;

import android.util.Log;

import com.mironenko.task2_android.Presenter.NamesCollections;
import com.mironenko.task2_android.Presenter.TasksList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListLab {
    private final String LOG_TAG = "ListLab log";

    private final TasksList[] tasksList = {TasksList.ADDING_IN_THE_BEGINNING,
            TasksList.ADDING_IN_THE_MIDDLE, TasksList.ADDING_IN_THE_END,
            TasksList.SEARCH_BY_VALUE, TasksList.REMOVING_IN_THE_BEGINNING,
            TasksList.REMOVING_IN_THE_MIDDLE, TasksList.REMOVING_IN_THE_END};

    private final TasksList[] tasksMap = {TasksList.ADDING_NEW,
            TasksList.SEARCH_BY_KEY, TasksList.REMOVING};

    private final NamesCollections[] nameList = {NamesCollections.ARRAY_LIST, NamesCollections.LINKED_LIST, NamesCollections.COPY_ON_WRITE_ARRAY_LIST};

    private final NamesCollections[] nameMap = {NamesCollections.TREE_MAP, NamesCollections.HASH_MAP};

    private final String NEW_MY_COLLECTION = "myCollection";

    private final String NEW_MY_MAP = "myMap";

    private final int collectionSize;

    private static ListLab listLab;

    private final List<Integer> basicList = new ArrayList<>();

    private final Map<String, Integer> basicMap = new HashMap<>();

    private final List<MyList> myLists;

    private final List<MyList> myMaps;

    private ListLab(int collectionSize) {
        this.collectionSize = collectionSize;
        myLists = new ArrayList<>();
        myMaps = new ArrayList<>();
        instanceCollection(NEW_MY_COLLECTION);
        instanceCollection(NEW_MY_MAP);
    }

    public static ListLab getListLab(int collectionSize) {
        if (listLab == null) {
            listLab = new ListLab(collectionSize);
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
                fillObjectsData(tasksList, nameList, myLists);
            case NEW_MY_MAP:
                initialMap();
                fillObjectsData(tasksMap, nameMap, myMaps);
        }
    }

    private void fillObjectsData(TasksList[] tasks, NamesCollections[] names, List<MyList> myList) {
        for (int i = 0; i < tasks.length; i++) {
            for (int j = 0; j < names.length; j++) {
                MyList myCollection = new MyList(names[j], tasks[i], collectionSize, basicList, basicMap);
                myList.add(myCollection);
            }
        }
    }

    private void initialList() {
        for (int i = 0; i < collectionSize; i++) {
            Log.d(LOG_TAG, "Initial LIST element " + i);
            basicList.add(i);
        }
        Log.d(LOG_TAG, "testList SIZE " + basicList.size());
    }

    private void initialMap() {
        for (int i = 0; i < collectionSize; i++) {
            Log.d(LOG_TAG, "Initial MAP element " + i);
            basicMap.put("key" + i, i);
        }
        Log.d(LOG_TAG, "testMap SIZE " + basicMap.size());
    }
}
