package com.lethalcoders.movieapi.domain.movie.service;

import com.lethalcoders.movieapi.domain.movie.exceptions.MovieNotFoundException;
import com.lethalcoders.movieapi.domain.movie.models.Movie;
import com.lethalcoders.movieapi.domain.movie.repos.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Slf4j
public class MovieServiceImpl implements MovieService{
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie findByName(String name) throws MovieNotFoundException {
        Optional<Movie> movieOptional = movieRepository.findByName(name);
        if(movieOptional.isEmpty()){
            throw new MovieNotFoundException("Movie name not found: " +  name);
        }
        return movieOptional.get();
    }

    @Override
    public Movie create(Movie item) {
        return movieRepository.save(item);
    }

    @Override
    public Movie findById(Long id) throws MovieNotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isEmpty()){
            throw new MovieNotFoundException("Movie id not found: " + id);
        }
        return movieOptional.get();
    }

    @Override
    public Iterable<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie update(Movie item) {
        item.setId(item.getId());
        return movieRepository.save(item);
    }

    @Override
    public void delete(Long id) throws MovieNotFoundException {
        Optional<Movie> movieExistOption = movieRepository.findById(id);
        if(movieExistOption.isEmpty())
            throw new MovieNotFoundException("No movie with id" + id);
        Movie movieToRemove = movieExistOption.get();
        movieRepository.delete(movieToRemove);
    }
}
