package com.lethalcoders.movieapi.domain.theater.service;

import com.lethalcoders.movieapi.domain.core.interfaces.CrudService;
import com.lethalcoders.movieapi.domain.theater.exceptions.TheaterNotFoundException;
import com.lethalcoders.movieapi.domain.theater.models.Theater;

public interface TheaterService extends CrudService<Theater, Long> {
    Theater findByMovie(String movie) throws TheaterNotFoundException;
}
