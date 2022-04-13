package com.lethalcoders.movieapi.domain.movieticket.repos;

import com.lethalcoders.movieapi.domain.movieticket.models.MovieTicket;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieTicketRepository extends CrudRepository<MovieTicket,Long> {
    Optional<MovieTicket> findByName(String name);
}
