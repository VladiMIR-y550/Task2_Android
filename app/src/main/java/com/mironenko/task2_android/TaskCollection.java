package com.mironenko.task2_android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskCollection {

    List<Integer> newListForTask;
    Map<String, Integer> newMapForTask;

    public Long startTask(List<Integer> list, ListName listName, TaskName taskName) {
        newListForTask = createListForTask(list, listName);
        return countDown(newListForTask, taskName);
    }
    public Long startTaskMap(Map<String, Integer> map, ListName listName, TaskName taskName) {
        newMapForTask = createMapForTask(map, listName);
        return countDownMap(newMapForTask, taskName);
    }

    private List<Integer> createListForTask (List<Integer> list, ListName listName) {
        switch (listName) {
            case ARRAY_LIST:
                return new ArrayList<>(list);
            case LINKED_LIST:
                return new LinkedList<>(list);
            case COPY_ON_WRITE_LIST:
                return new CopyOnWriteArrayList<>(list);
            default:
                break;
        }
        return null;
    }

    private Map<String, Integer> createMapForTask (Map<String, Integer> map, ListName listName) {
        switch (listName) {
            case TREE_MAP:
                return new TreeMap<>(map);
            case HASH_MAP:
                return new HashMap<>(map);
            default:
                break;
        }
        return null;
    }

    private long countDown(List<Integer> newListForTask, TaskName taskName) {
        long before = System.currentTimeMillis();
        choseTask(newListForTask, taskName);
        long after = System.currentTimeMillis();
        return after - before;
    }

    private long countDownMap (Map<String, Integer> map, TaskName taskName) {
        long before = System.currentTimeMillis();
        choseTaskMap(map, taskName);
        long after = System.currentTimeMillis();
        return after - before;
    }

    private void choseTask(List<Integer> list, TaskName taskName) {
        int value = 1;
        switch (taskName) {
            case ADDING_IN_THE_BEGINNING:
                list.add(0, value);
                break;
            case ADDING_IN_THE_MIDDLE:
                list.add(list.size() / 2, value);
                break;
            case ADDING_IN_THE_END:
                list.add(list.size(), value);
                break;
            case SEARCH_BY_VALUE:
                list.indexOf(1);
                break;
            case REMOVING_IN_THE_BEGINNING:
                list.remove(0);
                break;
            case REMOVING_IN_THE_MIDDLE:
                list.remove(list.size() / 2);
                break;
            case REMOVING_IN_THE_END:
                list.remove(list.size()-1);
                break;
            default:
                break;
        }
    }

    private void choseTaskMap(Map<String, Integer> map, TaskName taskName) {
        int value = 1;
        switch (taskName) {
            case ADDING_NEW:
                map.put("" + newMapForTask.size(), value);
                break;
            case SEARCH_BY_KEY:
                map.get("" + newMapForTask.size() / 2);
                break;
            case REMOVING:
                map.remove("" + newMapForTask.size() / 2);
                break;
            default:
                break;
        }
    }
}