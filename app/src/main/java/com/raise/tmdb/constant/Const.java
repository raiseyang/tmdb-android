package com.raise.tmdb.constant;

import com.raise.tmdb.App;

/**
 * Created by raise.yang on 17/08/10.
 */

public class Const {

    public static String movie_file_name_gz = "movie_file.gz";
    public static String movie_file_name = "movie_file.txt";

    public static String get_movie_file_gz() {
        return App.get().getFilesDir() + "/" + movie_file_name_gz;
    }

    public static String get_movie_file() {
        return App.get().getFilesDir() + "/" + movie_file_name;
    }

}
