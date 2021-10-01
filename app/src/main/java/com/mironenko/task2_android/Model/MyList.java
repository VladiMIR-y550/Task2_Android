package com.mironenko.task2_android.Model;

import android.util.Log;

import com.mironenko.task2_android.Presenter.NamesCollections;
import com.mironenko.task2_android.Presenter.TasksList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyList {

    public final String LOG_TAG = "myList TAG";

    private final String nameItem;

    private final NamesCollections namesCollections;

    private final TasksList task;

    private final int collectionSize;

    private String timeComplete;

    private List<Integer> testCollection;

    private Map<String, Integer> testMap;

    public MyList(NamesCollections namesCollections, TasksList task, int collectionSize, List<Integer> basicList, Map<String, Integer> basicMap) {
        this.namesCollections = namesCollections;
        this.task = task;
        this.nameItem = namesCollections.getTitleName();
        this.collectionSize = collectionSize;
        createCollectionForTest(namesCollections, basicList, basicMap);
    }

    public String getNameItem() {
        return nameItem;
    }

    public String startCalculate() {
        long result;
        String resultToString;

        long before = System.currentTimeMillis();
        choseTask(task);
        long after = System.currentTimeMillis();
        result = after - before;
        resultToString = Long.toString(result);
        timeComplete = resultToString;
        return resultToString;
    }

    private void createCollectionForTest(NamesCollections nameCollection, List<Integer> basicList, Map<String, Integer> basicMap) {
        switch (nameCollection) {
            case ARRAY_LIST:
                testCollection = new ArrayList<>(basicList);
            case LINKED_LIST:
                testCollection = new LinkedList<>(basicList);
            case COPY_ON_WRITE_ARRAY_LIST:
                testCollection = new CopyOnWriteArrayList<>(basicList);
            case TREE_MAP:
                testMap = new TreeMap<>(basicMap);
            case HASH_MAP:
                testMap = new HashMap<>(basicMap);
        }
    }

    private void choseTask(TasksList task) {
        Log.d(LOG_TAG, "Choice Task " + task.toString());
        int value = 1;
        switch (task) {
            case ADDING_IN_THE_BEGINNING:
                testCollection.add(0, value);
                break;
            case ADDING_IN_THE_MIDDLE:
                testCollection.add(testCollection.size() / 2, value);
                break;
            case ADDING_IN_THE_END:
                testCollection.add(testCollection.size() - 1, value);
                break;
            case SEARCH_BY_VALUE:
                testCollection.indexOf(value);
                break;
            case REMOVING_IN_THE_BEGINNING:
                testCollection.remove(0);
                break;
            case REMOVING_IN_THE_MIDDLE:
                testCollection.remove(testCollection.size() / 2);
                break;
            case REMOVING_IN_THE_END:
                testCollection.remove(testCollection.size() - 1);
                break;
            case ADDING_NEW:
                testMap.put("key" + (collectionSize + 1), value);
                break;
            case SEARCH_BY_KEY:
                testMap.get("key1");
                break;
            case REMOVING:
                testMap.remove("key1");
                break;
            default:
                break;
        }
    }
}
