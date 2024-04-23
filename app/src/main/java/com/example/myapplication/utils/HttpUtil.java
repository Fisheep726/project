package com.example.myapplication.utils;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.myapplication.bean.Constants;
import com.example.myapplication.bean.Result;
import com.example.myapplication.listener.ResultListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    private HttpUtil() {
    }

    private static HttpUtil httpUtil;
    private static OkHttpClient client;

    public static HttpUtil getInstance() {
        if (httpUtil == null) {
            client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(true).build();
            httpUtil = new HttpUtil();
        }
        return httpUtil;
    }

    public <T> void post(String url, Map<String, String> params,Class<T> tClass, ResultListener<T> listener) {
        if (params == null) {
            params = new HashMap<>();
        }

        String json = JSON.toJSONString(params);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder().url(Constants.BASE_URL + url).post(body).build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int code = response.code();
                if (code == 200 &&response.body()!=null) {
                    String json = response.body().string() ;

                    Log.e("TAG", "onResponse: "+json );

                    Result result = JSONObject.parseObject(json, new TypeReference<Result>() {});

                    JSONObject jsonObject = result.getData();


                    if (result.getCode() == 1) {//成功
                        T data = JSON.parseObject(JSON.toJSONString(jsonObject), tClass);
                        listener.onSuccess(data);
                    } else {
                        listener.onFailure(result.getMsg());
                    }
                } else {
                    listener.onFailure("请求失败");
                }
            }
        });

    }

    public <T> void get(String url,Map<String,String> params,Class<T> tClass,ResultListener<T> listener){
        if(params == null){
            params = new HashMap<>();
        }
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL + url).newBuilder();
        for (Map.Entry<String,String> entry : params.entrySet()){
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        String finalUrl = urlBuilder.build().toString();

        Request request = new Request.Builder().url(finalUrl).get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                int code = response.code();
                if (code == 200 &&response.body()!=null) {
                    String json = response.body().string() ;

                    Log.e("TAG", "onResponse: "+json );



                    Result result = JSONObject.parseObject(json, new TypeReference<Result>() {});

                    JSONObject jsonObject = result.getData();


                    if (result.getCode() == 1) {//成功
                        if (result.getList()!=null){
                            List<JSONObject> list = result.getList();
                            List<T> ts = JSON.parseArray(JSON.toJSONString(list), tClass);
                            listener.onSuccess(ts);
                        }else {
                            T data = JSON.parseObject(JSON.toJSONString(jsonObject), tClass);
                            listener.onSuccess(data);
                        }

                    } else {
                        listener.onFailure(result.getMsg());
                    }
                } else {
                    listener.onFailure("请求失败");
                }
            }
        });

    }

    public static void sendOkHttpRequest(Context context, String address, okhttp3.Callback callback) {


        Request request = new Request.Builder().url(address).removeHeader("User-Agent")
//                .addHeader("Connection", "keep-alive")
                .addHeader("User-Agent", getUserAgent(context))
//                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .build();
        client.newCall(request).enqueue(callback);
    }

    private static String getUserAgent(Context context) {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(context);
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
