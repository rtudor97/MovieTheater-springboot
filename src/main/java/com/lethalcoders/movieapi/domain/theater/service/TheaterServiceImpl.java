package com.lethalcoders.movieapi.domain.theater.service;

import com.lethalcoders.movieapi.domain.customer.exceptions.CustomerNotFoundException;
import com.lethalcoders.movieapi.domain.movie.exceptions.MovieNotFoundException;
import com.lethalcoders.movieapi.domain.theater.exceptions.TheaterNotFoundException;
import com.lethalcoders.movieapi.domain.theater.models.Theater;
import com.lethalcoders.movieapi.domain.theater.repos.TheaterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TheaterServiceImpl implements  TheaterService {

    private TheaterRepository theaterRepository;

    @Autowired
    public TheaterServiceImpl(TheaterRepository theaterRepository){
        this.theaterRepository = theaterRepository;
    }

    @Override
    public Theater findByMovie(String movie) throws TheaterNotFoundException {
        Optional<Theater> theaterOptional = theaterRepository.findByMovie(movie);
        if(theaterOptional.isEmpty()) {
            throw new TheaterNotFoundException("Movie Theater not found: " + movie);
        }
        return theaterOptional.get();
    }

    @Override
    public Theater create(Theater item) {
        return theaterRepository.save(item);
    }

    @Override
    public Theater findById(Long id) throws TheaterNotFoundException {
        Optional<Theater> theaterOptional = theaterRepository.findById(id);
        if(theaterOptional.isEmpty()){
            throw new TheaterNotFoundException("No theater with id: " + id);
        }
        return theaterOptional.get();
    }

    @Override
    public Iterable<Theater> findAll() {
        return theaterRepository.findAll();
    }

    @Override
    public Theater update(Theater item) {
        item.setId(item.getId());
        return theaterRepository.save(item);
    }

    @Override
    public void delete(Long id) throws TheaterNotFoundException {
        Optional<Theater> theaterOptional = theaterRepository.findById(id);
        if(theaterOptional.isEmpty()){
            throw new TheaterNotFoundException("No theater with id: " + id);
        }
        Theater theaterToRemove = theaterOptional.get();
        theaterRepository.delete(theaterToRemove);
    }
}
