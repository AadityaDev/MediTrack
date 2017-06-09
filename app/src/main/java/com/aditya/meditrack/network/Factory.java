package com.aditya.meditrack.network;

import android.os.StrictMode;

import com.aditya.meditrack.services.MoviesService;
import com.aditya.meditrack.services.ProductService;

import net.jcip.annotations.GuardedBy;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class Factory {

    private static final Object LOCK = new Object();
    public static final int TIMEOUT_IN_SECONDS = 60;

    @GuardedBy("LOCK")
    private static OkHttpClient mOkHttpClient;
    @GuardedBy("LOCK")
    private static ProductService songService;
    @GuardedBy("LOCK")
    private static MoviesService moviesService;

    public static OkHttpClient getOkHTTPClient() {
        synchronized (LOCK) {
            if (mOkHttpClient == null) {
                mOkHttpClient = new OkHttpClient.Builder()
                        .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                        .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                        .build();
            }
        }
        return mOkHttpClient;
    }

    public static ProductService getSongService() {
        synchronized (LOCK) {
            if (songService == null) {
                songService = new ProductService();
            }
        }
        return songService;
    }

    public static MoviesService getMoviesService() {
        synchronized (LOCK) {
            if (moviesService == null) {
                moviesService = new MoviesService();
            }
        }
        return moviesService;
    }

    public static void setUpThreadPolicy() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


}
