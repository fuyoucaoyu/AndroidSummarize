package com.justnow.androidsummarize.okhttp.socket;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.ByteString;

public class MockSocketServer {

    private static final String TAG = "MockSocketServer";

    private IServerStatusCallback mCallback;

    public MockSocketServer() {

    }

    public static  MockSocketServer newInstance() {
        return new MockSocketServer();
    }

    public void startServer(IServerStatusCallback callback) {
        mCallback = callback;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = startMockServer();
                Log.e(TAG, "startServer:" + url);
                if (null != mCallback) {
                    mCallback.onServerStart(url);
                }
            }
        }).start();
    }

    private String startMockServer() {
        MockWebServer mockWebServer = new MockWebServer();
        MockResponse response = new MockResponse().withWebSocketUpgrade(
                new WebSocketListener() {
                    @Override
                    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                        super.onClosed(webSocket, code, reason);

                        Log.e(TAG, "onClosed：");
                    }

                    @Override
                    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                        super.onClosing(webSocket, code, reason);
                    }

                    @Override
                    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                        super.onFailure(webSocket, t, response);

                        Log.e(TAG, "onFailure：" + t + " - " + response);
                    }

                    @Override
                    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                        super.onMessage(webSocket, text);

                        Log.e(TAG, "onMessage：" + text);
                    }

                    @Override
                    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                        super.onMessage(webSocket, bytes);
                    }

                    @Override
                    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                        super.onOpen(webSocket, response);

                        Log.e(TAG, "Server receive client connect request, " + response.message());
                        webSocket.send("Hello, this is server");
                    }
                }
        );

        mockWebServer.enqueue(response);
        String websocketUrl = "ws://" + mockWebServer.getHostName() + ":" + mockWebServer.getPort() + "/";
        return websocketUrl;
    }

    public interface IServerStatusCallback {
        public void onServerStart(String url);
    }
}
