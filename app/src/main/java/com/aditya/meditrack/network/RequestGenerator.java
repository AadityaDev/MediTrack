package com.aditya.meditrack.network;

import android.support.annotation.NonNull;

import com.aditya.meditrack.constants.AppAPI;

import okhttp3.MediaType;
import okhttp3.Request;

public class RequestGenerator {

    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/json; charset=utf-8");

    private static void addDefaultHeaders(@NonNull Request.Builder builder) {
        builder.addHeader(AppAPI.Headers.ACCESS_CONTROL_ALLOW_HEADERS, AppAPI.Headers.ALLOW_HEADERS);
        builder.addHeader(AppAPI.Headers.ACCESS_CONTROL_ALLOW_METHODS, AppAPI.Headers.ALLOW_METHODS);
        builder.addHeader(AppAPI.Headers.ACCESS_CONTROL_ALLOW_ORIGIN, AppAPI.Headers.ALLOW_ORIGIN);
        builder.addHeader(AppAPI.Headers.SERVER,AppAPI.Headers.CLOUDFLARE_NGINX);
    }


    public static Request get(@NonNull String url) {
        Request.Builder builder = new Request.Builder().url(url);
        addDefaultHeaders(builder);
        return builder.build();
    }

}
