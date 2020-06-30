package com.justnow.androidsummarize.okhttp.interceptor;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class StatisticsInterceptor implements Interceptor {

    private static final String TAG = "Statistics";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();

        Log.d(TAG, "startTime: " + System.nanoTime());
        Log.d(TAG, String.format("Send request %s on %s with %s", request.url(),
                chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        Log.d(TAG, "endTime: " + System.nanoTime());
        Log.d(TAG, String.format("Received response from %s with %s",
                response.request().url(),  response.headers()));

        return response;
    }
}
