package com.example.vyapaar.login.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.1.104:5000";
    private static final String BASE_URL_ASTRO = "https://api.vedicrishiastro.com/v1";
    private static final String userID = "613765";
    private static final String apiKey = "b0b243d52ddf9b931e80db617d92d7ad";

    /**
     * Create an instance of Retrofit object
     * */
    public static Retrofit getRetrofitInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitInstanceAstro() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        String credential = Credentials.basic(userID, apiKey);
        client.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {

                /*Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", "your-actual-api-key")
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);*/
                Request requestBuilder = chain.request().newBuilder()
                        .addHeader("Authorization","Basic "+credential).build();
                return chain.proceed(requestBuilder);
            }
        });

        /*RequestBody formBody = new FormEncodingBuilder()
                .add("day", "15")
                .add("month", "8")
                .add("year", "1987")
                .add("hour", "00")
                .add("min", "49")
                .add("lat", "28.4700")
                .add("lon", "77.0300")
                .add("tzone", "5.5")
                .build();*/
        client.addInterceptor(interceptor).build();
        OkHttpClient okHttpClient = client.build();
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
