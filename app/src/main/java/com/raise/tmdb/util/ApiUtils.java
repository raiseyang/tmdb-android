package com.raise.tmdb.util;

/**
 * Created by raise.yang on 17/08/08.
 */

public class ApiUtils {

    private static String image_url = "https://image.tmdb.org/t/p/w300%s";

    public static String get_image_url(String img) {
        return String.format(image_url, img);
    }

    public static String get_newest_date() {
        return "04_28_2017";
    }

}
