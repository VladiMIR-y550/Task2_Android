package com.mironenko.task2_android;

import static com.mironenko.task2_android.CollectionFragment.MSG_SHOW_RESULT;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class MyJob extends Thread{
    private static final String LOG_TAG = "MyThreadJob";
    private final Handler handler;
    private final DataCell dataCell;

    public MyJob(Handler handler, DataCell dataCell) {
        this.handler = handler;
        this.dataCell = dataCell;

    }
    @Override
    public void run() {
        super.run();
        Log.d(LOG_TAG, "Thread start = " + Thread.currentThread().getName());
        long result = startCalculate(dataCell);
        dataCell.setTimeComplete(result);
        dataCell.setCalculated(true);
        Log.d(LOG_TAG, "" + Thread.currentThread().getName() + " CollectionName "
                + dataCell.getNamesCollections() + " Task "
                + dataCell.getTask() + " Result = " + result);
        Message msg = Message.obtain();
        msg.what = MSG_SHOW_RESULT;
        msg.obj = dataCell;
        handler.sendMessage(msg);
    }

    public long startCalculate(DataCell dataCell) {
        long before = SystemClock.currentThreadTimeMillis();

        startTask(dataCell);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long after = SystemClock.currentThreadTimeMillis();

        return after - before;
    }

    private void startTask(DataCell dataCell) {
        int value = 2;
        switch (dataCell.getTask()) {
            case ADDING_IN_THE_BEGINNING:
                dataCell.getTestCollection().add(0, value);
                break;
            case ADDING_IN_THE_MIDDLE:
                dataCell.getTestCollection().add(dataCell.getTestCollection().size() / 2, value);
                break;
            case ADDING_IN_THE_END:
                dataCell.getTestCollection().add(dataCell.getTestCollection().size() - 1, value);
                break;
            case SEARCH_BY_VALUE:
                dataCell.getTestCollection().indexOf(value);
                break;
            case REMOVING_IN_THE_BEGINNING:
                dataCell.getTestCollection().remove(0);
                break;
            case REMOVING_IN_THE_MIDDLE:
                dataCell.getTestCollection().remove(dataCell.getTestCollection().size() / 2);
                break;
            case REMOVING_IN_THE_END:
                dataCell.getTestCollection().remove(dataCell.getTestCollection().size() - 1);
                break;
            default:
                break;
        }
    }
}

