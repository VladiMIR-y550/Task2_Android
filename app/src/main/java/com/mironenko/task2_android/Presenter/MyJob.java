package com.mironenko.task2_android.Presenter;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.mironenko.task2_android.Model.MyList;
import java.util.List;

public class MyJob extends Thread {


    private final String LOG_TAG = "THREAD";
    private final int MSG_START_JOB = 1;

    private Message msg;
    private Handler handler;
    private MyList myList;
    private List<Integer> basicList;

    public MyJob(Handler handler) {
        this.handler = handler;
    }

    public void setMyList(MyList myList) {
        this.myList = myList;
    }

    public void setBasicList(List<Integer> basicList) {
        this.basicList = basicList;
    }

    @Override
    public void run() {
        super.run();
        Log.d(LOG_TAG, "THREAD № " + Thread.currentThread().getName());
        long result;
        result = myList.startCalculate(basicList);
        msg = Message.obtain(handler, MSG_START_JOB);
        handler.sendMessage(msg);
        Log.d(LOG_TAG, "Thread № " + Thread.currentThread().getName() + "stop. Result " + myList.getNameItem() + " - " + myList.getTask() + " = " + result);
    }


/**
 *       метод использует интерфейс callback, инициирован в RecyclerHolder
 */
//        public void notifyCallback(long result, final IJob callBack){
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                callBack.onComplete(result);
//            }
//        });
//    }
}
