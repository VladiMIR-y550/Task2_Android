package com.mironenko.task2_android;

import android.os.Handler;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Calculator {

    private static final String LOG_TAG = "MyCalculator";
    private static Calculator calculator;
    private Handler handler;
    private List<DataCell> dataCellList;
    private boolean calculationStart = false;
    private ExecutorService executorService = Executors.newFixedThreadPool(getNumberOfCores());

    public boolean isCalculationStart() {
        return calculationStart;
    }

    public static Calculator getInstance() {
        if (calculator == null) {
            calculator = new Calculator();
        }
        return calculator;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setDataCellList(List<DataCell> dataCellList) {
        this.dataCellList = dataCellList;
    }

    public void calculate() {

        if (!calculationStart) {
            Log.d(LOG_TAG, "DataListCell size" + dataCellList.size());
            for (DataCell dataCell : dataCellList) {
                MyJob myJob = new MyJob(dataCell);
                myJob.setHandler(handler);
                executorService.submit(myJob);
            }
            executorService.shutdown();
            calculationStart = true;
        }
    }

    private int getNumberOfCores() {
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        Log.d("Calculator", "NUMBER OF CORES IS = " + numberOfCores);
        return numberOfCores;
    }
}
