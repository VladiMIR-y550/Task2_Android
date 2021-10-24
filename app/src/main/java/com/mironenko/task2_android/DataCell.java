package com.mironenko.task2_android;

import com.mironenko.task2_android.NamesCollections;
import com.mironenko.task2_android.TasksList;

import java.util.List;

public class DataCell {

    private long timeComplete;
    private int viewId;
    private final TasksList task;
    private final NamesCollections namesCollections;
    private List<Integer> testCollection;


    public DataCell(TasksList task, NamesCollections namesCollections) {
        this.task = task;
        this.namesCollections = namesCollections;
    }

    public long getTimeComplete() {
        return timeComplete;
    }

    public void setTimeComplete(long timeComplete) {
        this.timeComplete = timeComplete;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public TasksList getTask() {
        return task;
    }

    public List<Integer> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(List<Integer> testCollection) {
        this.testCollection = testCollection;
    }

    public NamesCollections getNamesCollections() {
        return namesCollections;
    }
}
