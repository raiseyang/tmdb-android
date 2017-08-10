package com.raise.tmdb.data;

import com.raise.tmdb.data.local.MovieLocalDataSource;
import com.raise.tmdb.data.model.Movie;
import com.raise.tmdb.data.remote.MovieRemoteDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by raise.yang on 17/08/10.
 */

public class MovieRepository implements MovieDataSource {

    private static MovieRepository s_instance;

    private MovieLocalDataSource m_localDataSource;
    private MovieRemoteDataSource m_remoteDataSource;

    private boolean m_cache_is_dirty;
    private Object m_remoteMovies;

    private Map<Integer, Movie> m_cache_movies = new HashMap<>();

    public static MovieRepository getInstance() {
        if (s_instance == null) {
            s_instance = new MovieRepository();
            s_instance.m_localDataSource = new MovieLocalDataSource();
            s_instance.m_remoteDataSource = new MovieRemoteDataSource();
        }
        return s_instance;
    }

    @Override
    public Observable<List<Movie>> getMovies() {
        if (m_cache_is_dirty) {
            getRemoteMovies();
        }
        return null;
    }

    @Override
    public Observable<Movie> getMovie(@NonNull int movie_id) {

        Movie movie = m_cache_movies.get(movie_id);
        if (movie != null) {
            return Observable.just(movie);
        }

        Observable<Movie> local_movie = m_localDataSource.getMovie(movie_id);
        Observable<Movie> remote_movie = m_remoteDataSource.getMovie(movie_id)
                .doOnNext(movie1 -> {
                    m_localDataSource.saveMovie(movie1);
                    m_cache_movies.put(movie1.getId(), movie1);//加入内存缓存
                });

        return remote_movie;

//        return Observable.concat(local_movie, remote_movie)//合并发射多个Observable,有序的
//                .firstElement()
//                .toObservable();//取第一个不为null的
    }

    @Override
    public void saveMovie(Movie movie) {

    }

    @Override
    public void refresh_movies() {

    }

    public Observable<List<Movie>> getRemoteMovies() {
        return m_remoteDataSource.getMovies()
                .flatMap(new Function<List<Movie>, ObservableSource<List<Movie>>>() {
                    @Override
                    public ObservableSource<List<Movie>> apply(List<Movie> movies) throws Exception {
                        return Observable.fromIterable(movies).
                                doOnNext(new Consumer<Movie>() {
                                    @Override
                                    public void accept(Movie movie) throws Exception {
                                        m_localDataSource.saveMovie(movie);
                                    }
                                }).toList().toObservable();
                    }
                });
    }
}
