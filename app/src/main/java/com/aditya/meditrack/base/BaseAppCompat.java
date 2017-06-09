package com.aditya.meditrack.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aditya.meditrack.network.Factory;

public class BaseAppCompat extends AppCompatActivity {

    private final String TAG=this.getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Factory.setUpThreadPolicy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public String getTAG() {
        return TAG;
    }

    public void finish() {
        /*
         * This can only invoked by the user or the app finishing the activity
         * by navigating from the activity so the HOME key was not pressed.
         */
        super.finish();
    }
}
