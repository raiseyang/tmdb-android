package com.raise.tmdb.http;

import com.raise.tmdb.BuildConfig;
import com.raise.tmdb.data.model.Movie;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * Created by raise.yang on 17/08/08.
 */

public interface TmdbApi {

    @Streaming
    @GET("movie_ids_{date}.json.gz")
    Observable<ResponseBody> daily_file(@Path("date") String date);

    @GET("movie/{movie_id}?api_key=" + BuildConfig.api_key)
    Observable<Movie> movie_by_id(@Path("movie_id") int id);

}
