package com.example.alherd.carapp.adapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ExecutorServiceCreator {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private ExecutorServiceCreator() {
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }
}