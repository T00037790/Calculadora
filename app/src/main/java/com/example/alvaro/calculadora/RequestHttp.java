package com.example.alvaro.calculadora;

/**
 * Created by Alvaro on 10/10/2016.
 */
import com.loopj.android.http.*;
public class RequestHttp {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }
}
