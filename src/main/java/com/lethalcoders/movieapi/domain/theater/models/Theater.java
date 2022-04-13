package com.lethalcoders.movieapi.domain.theater.models;

import com.lethalcoders.movieapi.domain.core.models.BaseClass;
import com.lethalcoders.movieapi.domain.movie.models.Movie;
import com.lethalcoders.movieapi.domain.movieticket.models.MovieTicket;

public class Theater extends BaseClass {

    private Movie movie;
    Integer seatCapacity;

    public Theater(){
    }

    public Theater(Movie movie, Integer seatCapacity) {
        this.movie = movie;
        this.seatCapacity = seatCapacity;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Integer getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(Integer seatCapacity) {
        this.seatCapacity = seatCapacity;
    }
}
