package com.mironenko.task2_android;

import static com.mironenko.task2_android.CollectionFragment.MSG_SHOW_RESULT;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MyJob extends Thread{
    private static final String LOG_TAG = "MyThreadJob";
    private final Handler handler;
    private final DataCell dataCell;
    private final Calculator calculator;

    public MyJob(Handler handler, DataCell dataCell, Calculator calculator) {
        this.handler = handler;
        this.dataCell = dataCell;
        this.calculator = calculator;
    }
    @Override
    public void run() {
        super.run();
        Log.d(LOG_TAG, "Thread start = " + Thread.currentThread().getName());
        long result = calculator.startCalculate(dataCell);
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
}

