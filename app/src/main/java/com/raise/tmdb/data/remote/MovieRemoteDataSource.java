package com.raise.tmdb.data.remote;

import com.raise.tmdb.data.MovieDataSource;
import com.raise.tmdb.data.model.Movie;
import com.raise.tmdb.http.RetrofitProvider;
import com.raise.tmdb.http.TmdbApi;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by raise.yang on 17/08/10.
 */

public class MovieRemoteDataSource implements MovieDataSource{
    @Override
    public Observable<List<Movie>> getMovies() {
        return null;
    }

    @Override
    public Observable<Movie> getMovie(int movie_id) {
        return RetrofitProvider.getInstance()
                .get_movie_server()
                .create(TmdbApi.class)
                .movie_by_id(movie_id);
    }

    @Override
    public void saveMovie(Movie movie) {

    }

    @Override
    public void refresh_movies() {

    }
}
