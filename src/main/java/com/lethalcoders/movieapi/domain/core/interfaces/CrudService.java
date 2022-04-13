package com.lethalcoders.movieapi.domain.core.interfaces;

import com.lethalcoders.movieapi.domain.customer.exceptions.CustomerNotFoundException;
import com.lethalcoders.movieapi.domain.movie.exceptions.MovieNotFoundException;
import com.lethalcoders.movieapi.domain.movieticket.exceptions.MovieTicketNotFoundException;
import com.lethalcoders.movieapi.domain.theater.exceptions.TheaterNotFoundException;

public interface CrudService <T,I>{
    T create(T item);
    T findById(I id) throws MovieNotFoundException, CustomerNotFoundException, MovieTicketNotFoundException, TheaterNotFoundException;
    Iterable<T> findAll();
    T update(T item);
    void delete(I id) throws MovieNotFoundException, CustomerNotFoundException, MovieTicketNotFoundException, TheaterNotFoundException;

}
