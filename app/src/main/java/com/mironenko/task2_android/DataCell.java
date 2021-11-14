package com.mironenko.task2_android;

import java.util.List;

public class DataCell {

    private String timeComplete;
    private boolean calculated = false;
    private CellViewKeys viewKey;
    private final TasksList task;
    private final NamesCollections namesCollections;
    private List<Integer> testCollection;


    public DataCell(TasksList task, NamesCollections namesCollections) {
        this.task = task;
        this.namesCollections = namesCollections;
    }

    public String getTimeComplete() {
        return timeComplete;
    }

    public void setTimeComplete(String timeComplete) {
        this.timeComplete = timeComplete;
    }

    public CellViewKeys getViewKey() {
        return viewKey;
    }

    public void setViewKey(CellViewKeys viewKey) {
        this.viewKey = viewKey;
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

    public boolean isCalculated() {
        return calculated;
    }

    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }
}