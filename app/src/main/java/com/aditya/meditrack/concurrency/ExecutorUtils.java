package com.aditya.meditrack.concurrency;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorUtils {

    private static final int BG_POOL_SIZE = 3;
    private static final int DATABASE_POOL_SIZE = 1; // only one database thread for sync issues.
    private static final String APP_THREAD = "app_thread";
    private static final String BG_THREAD = "background_thread";
    private static final String DB_THREAD = "db_thread";

    private static Executor mUIThread = new UiThreadExecutor();
    private static ListeningExecutorService mAppThreadPool = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor(new NamedThreadFactory(APP_THREAD)));
    private static ListeningExecutorService mBackgroundThreadPool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(BG_POOL_SIZE, new NamedThreadFactory(BG_THREAD)));
    private static ListeningExecutorService mDbThreadPool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(DATABASE_POOL_SIZE, new NamedThreadFactory(DB_THREAD)));

    public static ListeningExecutorService getAppThread() {
        return mAppThreadPool;
    }

    public static Executor getUIThread() {
        return mUIThread;
    }

    public static ListeningExecutorService getBackgroundPool() {
        return mBackgroundThreadPool;
    }

    public static ListeningExecutorService getDatabaseThread() {
        return mDbThreadPool;
    }
}
