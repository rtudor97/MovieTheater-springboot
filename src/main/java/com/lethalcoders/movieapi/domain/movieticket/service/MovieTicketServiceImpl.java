package com.lethalcoders.movieapi.domain.movieticket.service;

import com.lethalcoders.movieapi.domain.movieticket.exceptions.MovieTicketNotFoundException;
import com.lethalcoders.movieapi.domain.movieticket.models.MovieTicket;
import com.lethalcoders.movieapi.domain.movieticket.repos.MovieTicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j

public class MovieTicketServiceImpl implements MovieTicketService {

    private MovieTicketRepository movieTicketRepository;

    @Autowired
    public MovieTicketServiceImpl(MovieTicketRepository movieTicketRepository) {
        this.movieTicketRepository = movieTicketRepository;
    }

    @Override
    public MovieTicket create(MovieTicket item) {
        item = movieTicketRepository.save(item);
        log.info("Saved item with id {}", item.getId());
        return item;
    }

    @Override
    public MovieTicket findById(Long id) throws MovieTicketNotFoundException {
        Optional<MovieTicket> movieTicketOptional = movieTicketRepository.findById(id);
        if (movieTicketOptional.isEmpty()) {
            throw new MovieTicketNotFoundException("Movie ticket id not found: " + id);
        }
        return movieTicketOptional.get();
    }

    @Override
    public Iterable<MovieTicket> findAll() {
        return movieTicketRepository.findAll();
    }

    @Override
    public MovieTicket update(MovieTicket item) {
        item.setId(item.getId());
        return movieTicketRepository.save(item);
    }

    @Override
    public void delete(Long id) throws MovieTicketNotFoundException {
        Optional<MovieTicket> movieTicketOption = movieTicketRepository.findById(id);
        if (movieTicketOption.isEmpty()) throw new MovieTicketNotFoundException("No movie ticket with id: " + id);
        MovieTicket movieTicketToRemove = movieTicketOption.get();
        movieTicketRepository.delete(movieTicketToRemove);


    }

    @Override
    public MovieTicket findByName(String name) throws MovieTicketNotFoundException {
        Optional<MovieTicket> movieTicketOptional = movieTicketRepository.findByName(name);
        if (movieTicketOptional.isEmpty()) {
            throw new MovieTicketNotFoundException("Movie ticket name not found: " + name);

        }
        return movieTicketOptional.get();
    }
}