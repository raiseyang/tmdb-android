package com.raise.tmdb.data;

import com.raise.tmdb.data.model.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by raise.yang on 17/08/10.
 */

public interface MovieDataSource {

    Observable<List<Movie>> getMovies();

    Observable<Movie> getMovie(int movie_id);

    void saveMovie(Movie movie);

    void refresh_movies();
}
