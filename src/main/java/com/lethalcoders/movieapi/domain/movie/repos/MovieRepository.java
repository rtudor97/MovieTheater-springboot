package com.lethalcoders.movieapi.domain.movie.repos;

import com.lethalcoders.movieapi.domain.movie.models.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    Optional<Movie> findByName(String name);
}
