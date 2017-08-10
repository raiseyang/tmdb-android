package com.raise.tmdb.data.model;

import java.util.List;

/**
 * Created by raise.yang on 17/08/09.
 */

public class Movie {

    /**
     * adult : false
     * backdrop_path : /wXZB5DV0sgdSlFRUrZdE7fBLGEH.jpg
     * belongs_to_collection : {"id":656,"name":"Saw Collection","poster_path":"/xmC2A7qPEfiOkQC58fZh8srWQ5v.jpg","backdrop_path":"/oLfS1lOmN2KIU2IQ200SDEPVEZe.jpg"}
     * budget : 1200000
     * genres : [{"id":27,"name":"Horror"},{"id":9648,"name":"Mystery"},{"id":80,"name":"Crime"}]
     * homepage :
     * id : 176
     * imdb_id : tt0387564
     * original_language : en
     * original_title : Saw
     * overview : Obsessed with teaching his victims the value of life, a deranged, sadistic serial killer abducts the morally wayward. Once captured, they must face impossible choices in a horrific game of survival. The victims must fight to win their lives back, or die trying...
     * popularity : 6.311572
     * poster_path : /dHYvIgsax8ZFgkz1OslE4V6Pnf5.jpg
     * production_companies : [{"name":"Lions Gate Films","id":35},{"name":"Twisted Pictures","id":2061},{"name":"Evolution Entertainment","id":23019},{"name":"Saw Productions Inc.","id":55405}]
     * production_countries : [{"iso_3166_1":"US","name":"United States of America"}]
     * release_date : 2004-10-01
     * revenue : 103911669
     * runtime : 103
     * spoken_languages : [{"iso_639_1":"en","name":"English"}]
     * status : Released
     * tagline : Live or die. Make your choice.
     * title : Saw
     * video : false
     * vote_average : 7.2
     * vote_count : 1978
     */

    private boolean adult;
    private String backdrop_path;
    private BelongsToCollectionEntity belongs_to_collection;
    private int budget;
    private String homepage;
    private int id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private int revenue;
    private int runtime;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;
    private List<GenresEntity> genres;
    private List<ProductionCompaniesEntity> production_companies;
    private List<ProductionCountriesEntity> production_countries;
    private List<SpokenLanguagesEntity> spoken_languages;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public BelongsToCollectionEntity getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(BelongsToCollectionEntity belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public List<GenresEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresEntity> genres) {
        this.genres = genres;
    }

    public List<ProductionCompaniesEntity> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<ProductionCompaniesEntity> production_companies) {
        this.production_companies = production_companies;
    }

    public List<ProductionCountriesEntity> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<ProductionCountriesEntity> production_countries) {
        this.production_countries = production_countries;
    }

    public List<SpokenLanguagesEntity> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<SpokenLanguagesEntity> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public static class BelongsToCollectionEntity {
        /**
         * id : 656
         * name : Saw Collection
         * poster_path : /xmC2A7qPEfiOkQC58fZh8srWQ5v.jpg
         * backdrop_path : /oLfS1lOmN2KIU2IQ200SDEPVEZe.jpg
         */

        private int id;
        private String name;
        private String poster_path;
        private String backdrop_path;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }
    }

    public static class GenresEntity {
        /**
         * id : 27
         * name : Horror
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ProductionCompaniesEntity {
        /**
         * name : Lions Gate Films
         * id : 35
         */

        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class ProductionCountriesEntity {
        /**
         * iso_3166_1 : US
         * name : United States of America
         */

        private String iso_3166_1;
        private String name;

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SpokenLanguagesEntity {
        /**
         * iso_639_1 : en
         * name : English
         */

        private String iso_639_1;
        private String name;

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
