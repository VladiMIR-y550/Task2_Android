package com.mironenko.task2_android.Model;

import android.util.Log;

import com.mironenko.task2_android.Presenter.NamesCollections;
import com.mironenko.task2_android.Presenter.TasksList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyList {

    public final String LOG_TAG = "myList TAG";

    private final int nameItem;

    private final NamesCollections namesCollections;

    private final TasksList task;

    private String timeComplete;

    private List<Integer> testCollection;

    private Map<String, Integer> testMap;

    public MyList(NamesCollections namesCollections, TasksList task) {
        this.namesCollections = namesCollections;
        this.task = task;
        this.nameItem = namesCollections.getTitleName();
//        createCollectionForTest(namesCollections, basicList, basicMap);
    }
    public String getTimeComplete() {
        return timeComplete;
    }

    public int getNameItem() {
        return nameItem;
    }

    public TasksList getTask() {
        return task;
    }

    public long startCalculate(List<Integer> basicList) {
        long result;
        createCollectionForTest(namesCollections, basicList);
        long before = System.currentTimeMillis();
        choseTask(task);
        long after = System.currentTimeMillis();
        result = after - before;
        timeComplete = Long.toString(result);;

        return result;
    }

    private void createCollectionForTest(NamesCollections nameCollection, List<Integer> basicList) {
        switch (nameCollection) {
            case ARRAY_LIST:
                testCollection = new ArrayList<>(basicList);
            case LINKED_LIST:
                testCollection = new LinkedList<>(basicList);
            case COPY_ON_WRITE_ARRAY_LIST:
                testCollection = new CopyOnWriteArrayList<>(basicList);
//            case TREE_MAP:
//                testMap = new TreeMap<>(basicMap);
//            case HASH_MAP:
//                testMap = new HashMap<>(basicMap);
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
//            case ADDING_NEW:
//                testMap.put("key" + (collectionSize + 1), value);
//                break;
//            case SEARCH_BY_KEY:
//                testMap.get("key1");
//                break;
//            case REMOVING:
//                testMap.remove("key1");
//                break;
            default:
                break;
        }
    }
}
