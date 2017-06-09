package com.aditya.meditrack.concurrency;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

public class UiThreadExecutor implements Executor {
    @Override
    public void execute(@NonNull Runnable command) {
        new Handler(Looper.getMainLooper()).post(command);
    }
}
