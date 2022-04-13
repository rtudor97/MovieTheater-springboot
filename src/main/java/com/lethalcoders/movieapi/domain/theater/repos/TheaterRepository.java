package com.lethalcoders.movieapi.domain.theater.repos;

import com.lethalcoders.movieapi.domain.theater.models.Theater;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TheaterRepository extends CrudRepository<Theater, Long> {
    Optional<Theater> findByMovie(String name);
}
