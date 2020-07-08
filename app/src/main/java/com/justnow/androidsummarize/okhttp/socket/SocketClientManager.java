package com.justnow.androidsummarize.okhttp.socket;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class SocketClientManager {

    private static final String TAG = "SocketClientManager";

    private final String mUrl;
    private final long mInterval;
    private final TimeUnit mIntervalUnit;
    private final long mTimeout;
    private final TimeUnit mTimeoutUnit;

    private OkHttpClient mOkHttpClient;
    private WebSocket mWebSocket;

    private SocketClientManager(Builder builder) {
        this.mUrl = builder.mUrl;
        this.mInterval = builder.mInterval;
        this.mIntervalUnit = builder.mIntervalUnit;
        this.mTimeout = builder.mTimeout;
        this.mTimeoutUnit = builder.mTimeoutUnit;

        init();
    }

    public String getmUrl() {
        return mUrl;
    }

    public long getmInterval() {
        return mInterval;
    }

    public TimeUnit getmIntervalUnit() {
        return mIntervalUnit;
    }

    public long getmTimeout() {
        return mTimeout;
    }

    public TimeUnit getmTimeoutUnit() {
        return mTimeoutUnit;
    }

    private void init() {
        mOkHttpClient = new OkHttpClient.Builder()
                .pingInterval(mInterval, mIntervalUnit) // OkHttp 自带的心跳
                .connectTimeout(mTimeout, mTimeoutUnit)
                .readTimeout(mTimeout, mTimeoutUnit)
                .writeTimeout(mTimeout, mTimeoutUnit)
                .build();
    }

    public void connect() {
        Request request = new Request.Builder().url(mUrl).build();
        mWebSocket = mOkHttpClient.newWebSocket(request, new CustomWebSocketListener());
    }

    public static class Builder {
        private String mUrl;
        private long mInterval;
        private TimeUnit mIntervalUnit;
        private long mTimeout;
        private TimeUnit mTimeoutUnit;

        public Builder() {

        }

        public Builder url(String url) {
            this.mUrl = url;
            return this;
        }

        public Builder pinInterval(long interval, TimeUnit unit) {
            this.mInterval = interval;
            this.mIntervalUnit = unit;
            return this;
        }

        public Builder timeout(long timeout, TimeUnit unit) {
            this.mTimeout = timeout;
            this.mTimeoutUnit = unit;
            return this;
        }

        public SocketClientManager build() {
            return new SocketClientManager(this);
        }
    }

    class CustomWebSocketListener extends WebSocketListener {
        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosed(webSocket, code, reason);

            Log.e(TAG, "OnClosed: " + code + " - " + reason);
        }

        @Override
        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);

            Log.e(TAG, "onFailure！" + t.getMessage());
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            super.onMessage(webSocket, text);

            Log.e(TAG, "onMessage:" + text);
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            super.onOpen(webSocket, response);

            //测试发消息
            webSocket.send("Hello, this is client");
        }
    }
}
