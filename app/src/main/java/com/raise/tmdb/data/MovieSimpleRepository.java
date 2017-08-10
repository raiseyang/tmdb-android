package com.raise.tmdb.data;

import com.google.gson.Gson;
import com.raise.tmdb.constant.Const;
import com.raise.tmdb.data.model.MovieSimple;
import com.raise.tmdb.util.FileUtils;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by raise.yang on 17/08/10.
 */

public class MovieSimpleRepository {

    private static MovieSimpleRepository s_instance;

    public static MovieSimpleRepository getInstance() {
        if (s_instance == null) {
            s_instance = new MovieSimpleRepository();
        }
        return s_instance;
    }

    public Flowable<MovieSimple> getMovieSimples() {
        return FileUtils.rx_read_file(Const.get_movie_file(), 20)
                .observeOn(Schedulers.io())
                .map(s -> new Gson().fromJson(s, MovieSimple.class))
                ;
    }


}
