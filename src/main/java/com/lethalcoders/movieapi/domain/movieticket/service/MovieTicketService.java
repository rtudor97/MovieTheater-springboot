package com.lethalcoders.movieapi.domain.movieticket.service;

import com.lethalcoders.movieapi.domain.core.interfaces.CrudService;
import com.lethalcoders.movieapi.domain.movieticket.exceptions.MovieTicketNotFoundException;
import com.lethalcoders.movieapi.domain.movieticket.models.MovieTicket;

public interface MovieTicketService extends CrudService<MovieTicket, Long> {
    MovieTicket findByName(String name) throws MovieTicketNotFoundException;
}