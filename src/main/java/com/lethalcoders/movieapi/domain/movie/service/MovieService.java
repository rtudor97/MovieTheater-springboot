package com.lethalcoders.movieapi.domain.movie.service;

import com.lethalcoders.movieapi.domain.core.interfaces.CrudService;
import com.lethalcoders.movieapi.domain.movie.exceptions.MovieNotFoundException;
import com.lethalcoders.movieapi.domain.movie.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieService extends CrudService<Movie, Long> {
    Movie findByName(String name) throws MovieNotFoundException;
}
