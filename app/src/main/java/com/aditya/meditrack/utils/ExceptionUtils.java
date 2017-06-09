package com.aditya.meditrack.utils;

import android.support.annotation.NonNull;
import android.util.Log;

public class ExceptionUtils {

    public static void exceptionMessage(@NonNull Exception exception, @NonNull String TAG) {
        if (StringUtils.isNotEmptyOrNull(exception.getMessage())) {
            Log.d(TAG, exception.getMessage());
        } else {
            Log.d(TAG, exception.getMessage());
        }
    }

    public static void throwableMessage(@NonNull Throwable throwable, @NonNull String TAG) {
        if (StringUtils.isNotEmptyOrNull(throwable.getMessage())) {
            Log.d(TAG, throwable.getMessage());
        } else {
            Log.d(TAG, throwable.getMessage());
        }
    }

}
