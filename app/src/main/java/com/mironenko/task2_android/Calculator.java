package com.mironenko.task2_android;

import static com.mironenko.task2_android.CollectionFragment.MSG_SHOW_RESULT;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.List;

public class Calculator {

    private static final String LOG_TAG = "MyCalculator";
    private static Calculator calculator;
    private final Handler handler;
    private final List<DataCell> dataCellList;

    private Calculator(List<DataCell> dataCellList, Handler handler) {
        this.dataCellList = dataCellList;
        this.handler = handler;
    }

    public static Calculator getInitialCalculator(List<DataCell> dataCellList, Handler handler) {
        if (calculator == null) {
            calculator = new Calculator(dataCellList, handler);
        }
        return calculator;
    }

    public void calculate() {
        Log.d(LOG_TAG, "DataListCell size" + dataCellList.size());
        for (DataCell dataCell : dataCellList) {
            new Thread(new Runnable() {
                Message msg;

                @Override
                public void run() {
                    Log.d(LOG_TAG, "Thread start = " + Thread.currentThread().getName());
                    long result = startCalculate(dataCell);
                    dataCell.setTimeComplete(result);
                    Log.d(LOG_TAG, "" + Thread.currentThread().getName() + " CollectionName " + dataCell.getNamesCollections() + " Task " + dataCell.getTask() + " Result = " + result);
                    msg = Message.obtain();
                    msg.what = MSG_SHOW_RESULT;
                    msg.obj = dataCell;
                    handler.sendMessageDelayed(msg, dataCell.getTimeComplete() * 20);
                }
            }).start();
        }
    }

    private long startCalculate(DataCell dataCell) {
        long before = System.currentTimeMillis();
        startTask(dataCell);
        long after = System.currentTimeMillis();
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
