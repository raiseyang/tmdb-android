package com.raise.tmdb.data.local;

import com.raise.tmdb.data.MovieDataSource;
import com.raise.tmdb.data.model.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Gson gson = new Gson();
 * Created by raise.yang on 17/08/09.
 */

public class MovieLocalDataSource implements MovieDataSource {
    private final String TAG = "MovieLocalDataSource";
    private static MovieLocalDataSource m_instance;

    public static MovieLocalDataSource getInstance() {
        if (m_instance == null)
            m_instance = new MovieLocalDataSource();
        return m_instance;
    }

    @Override
    public Observable<List<Movie>> getMovies() {
        return null;
    }

    @Override
    public Observable<Movie> getMovie(int movie_id) {
        return Observable.never();
    }

    @Override
    public void saveMovie(Movie movie) {

    }

    @Override
    public void refresh_movies() {

    }
}
