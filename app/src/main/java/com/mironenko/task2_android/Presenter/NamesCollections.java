package com.mironenko.task2_android.Presenter;

public enum NamesCollections {
    ARRAY_LIST("ArrayList"),
    LINKED_LIST("LinkedList"),
    COPY_ON_WRITE_ARRAY_LIST("CopyOnWriteArrayList"),
    TREE_MAP("TreeMap"),
    HASH_MAP("HashMap");

    private final String titleName;

    NamesCollections(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleName() {
        return titleName;
    }
}
