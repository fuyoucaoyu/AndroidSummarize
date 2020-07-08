package com.justnow.androidsummarize.okhttp;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.justnow.androidsummarize.R;
import com.justnow.androidsummarize.okhttp.interceptor.StatisticsInterceptor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/**
 * OkHttp是一个高效的HTTP客户端，它有以下默认特性：
 *      支持HTTP/2，允许所有同一个主机地址的请求共享同一个socket连接
 *      连接池减少请求延时
 *      透明的GZIP压缩减少响应数据的大小
 *      缓存响应内容，避免一些完全重复的请求
 * OkHttp使用现代TLS技术(SNI, ALPN)初始化新的连接，当握手失败时会回退到TLS 1.0。
 *
 * 用户可传入的 interceptor 分为两类：
 * ①一类是全局的 interceptor，该类 interceptor 在整个拦截器链中最早被调用，通过 OkHttpClient.Builder#addInterceptor(Interceptor) 传入；
 * ②另外一类是非网页请求的 interceptor ，这类拦截器只会在非网页请求中被调用，并且是在组装完请求之后，真正发起网络请求前被调用，
 *      所有的 interceptor 被保存在 List<Interceptor> interceptors 集合中，按照添加顺序来逐个调用
 *
 * 推荐让 OkHttpClient 保持单例，用同一个 OkHttpClient 实例来执行你的所有请求，
 * 因为每一个 OkHttpClient 实例都拥有自己的连接池和线程池
 *      OkHttpClient okHttpClient = new OkHttpClient();
 *      OkHttpClient client = okHttpClient.newBuilder(); // 共享连接池、线程池和配置信息
 *
 * ResponseBody 的数据内容“阅后即焚”
 */
public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "OkHttpTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        initView();
    }

    private void initView() {
        Button syncGet = findViewById(R.id.sync_get);
        Button asyncGet = findViewById(R.id.async_get);
        Button syncPost = findViewById(R.id.sync_post);
        Button asyncPost = findViewById(R.id.async_post);
        Button asyncPostStream = findViewById(R.id.async_post_stream);
        Button asyncPostFile = findViewById(R.id.async_post_file);

        syncGet.setOnClickListener(this);
        asyncGet.setOnClickListener(this);
        syncPost.setOnClickListener(this);
        asyncPost.setOnClickListener(this);
        asyncPostStream.setOnClickListener(this);
        asyncPostFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sync_get:
                syncGet();
                break;
            case R.id.async_get:
                asyncGet();
                break;
            case R.id.sync_post:
                syncPost();
                break;
            case R.id.async_post:
                asyncPost();
                break;
            case R.id.async_post_stream:
                asyncPostStream();
                break;
            case R.id.async_post_file:
                asyncPostFile();
                break;
        }
    }

    private void syncGet() {
        String url = "https://www.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    Log.d(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void asyncGet() {
        String url = "https://www.publicobject.com/helloworld.txt";
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new StatisticsInterceptor())
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    private void syncPost() {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.github.com/markdown/raw";
        MediaType type = MediaType.parse("text/x-markdown; charset=utf-8");
        String body = "Test Sync Post";
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(type, body))
                .build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    Log.d(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void asyncPost() {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.github.com/markdown/raw";
        MediaType type = MediaType.parse("text/x-markdown; charset=utf-8");
        String body = "Test Async Post";
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(type, body))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    private void asyncPostStream() {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.github.com/markdown/raw";
        final MediaType type = MediaType.parse("text/x-markdown; charset=utf-8");
        final String body = "Test Async Stream Post";

        RequestBody requestBody = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return type;
            }

            @Override
            public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8(body);
            }
        };

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse: \n " + response.protocol() + " " + response.code() + " " + response.message());

                Headers headers = response.headers();
                int len = headers.size();
                for (int i = 0; i < len; i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }

                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    private void asyncPostFile() {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "https://api.github.com/markdown/raw";
        final MediaType type = MediaType.parse("text/x-markdown; charset=utf-8");

        Log.d(TAG, this.getFilesDir().getAbsolutePath());
        Log.d(TAG, this.getCacheDir().getAbsolutePath());

        FileOutputStream fos = null;
        File targetFile = null;
        try {
            InputStream inputStream = this.getAssets().open("test.md");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);

            targetFile = new File(this.getFilesDir().getAbsolutePath() + "/test.md");
            if (!targetFile.exists()) {
                targetFile.createNewFile();
            }

            fos = new FileOutputStream(targetFile, true);
            fos.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                    fos = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(type, targetFile))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse: \n " + response.protocol() + " " + response.code() + " " + response.message());

                Headers headers = response.headers();
                int len = headers.size();
                for (int i = 0; i < len; i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }

                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }
}