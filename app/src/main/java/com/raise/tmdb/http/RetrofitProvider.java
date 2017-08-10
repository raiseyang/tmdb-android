package com.raise.tmdb.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.raise.trace.Trace;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by raise.yang on 17/08/08.
 */

public class RetrofitProvider {

    private static RetrofitProvider m_instance;

    public static RetrofitProvider getInstance() {
        if (m_instance == null)
            m_instance = new RetrofitProvider();
        return m_instance;
    }

    public <T> T create_file(Class<T> classz) {
        return get_file_server().create(classz);
    }

    private OkHttpClient okhttp() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Trace.i("RetrofitProvider","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    private Retrofit get_file_server() {
        //http://files.tmdb.org/p/exports/movie_ids_04_28_2017.json.gz
        return new Retrofit.Builder()
                .client(okhttp())
                .baseUrl("http://files.tmdb.org/p/exports/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Retrofit get_movie_server() {
        //https://api.themoviedb.org/3/movie/{movie_id}
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(okhttp())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Retrofit get_image_server() {
        //https://image.tmdb.org/t/p/w500/xxx.jag
        return new Retrofit.Builder()
                .baseUrl("https://image.tmdb.org/t/p/w300/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
