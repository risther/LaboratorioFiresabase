package com.example.laboratoriofiresabase;

public class Movie {
    public String id;
    public String movieName;
    public String moviePoster;
    public float movieRating;
    public Movie(){
    }
    public Movie(String id, String movieName, String moviePoster, float movieRating){
        this.id = id;
        this.movieName = movieName;
        this.moviePoster = moviePoster;
        this.movieRating  = movieRating;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public String getMoviePoster() {
        return moviePoster;
    }
    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }
    public float getMovieRating() {
        return movieRating;
    }
    public void setMovieRating(float movieRating) {
        this.movieRating = movieRating;
    }
}
