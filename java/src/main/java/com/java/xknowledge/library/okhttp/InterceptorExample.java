package com.java.xknowledge.library.okhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class InterceptorExample {
    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new CronetInterceptor()).build();
        Request request = new Request.Builder().url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example").build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class CronetInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            System.out.println(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            System.out.println(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }
}

