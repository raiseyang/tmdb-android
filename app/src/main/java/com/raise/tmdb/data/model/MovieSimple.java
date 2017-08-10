package com.raise.tmdb.data.model;

/**
 * Created by raise.yang on 17/08/09.
 */

public class MovieSimple {

    /**
     * adult : false
     * id : 176
     * original_title : Saw
     * popularity : 2.514102
     * video : false
     */

    private boolean adult;
    private int id;
    private String original_title;
    private double popularity;
    private boolean video;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }
}
