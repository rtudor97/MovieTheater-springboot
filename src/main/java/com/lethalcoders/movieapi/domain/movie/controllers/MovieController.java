package com.lethalcoders.movieapi.domain.movie.controllers;

import com.lethalcoders.movieapi.domain.movie.models.Movie;
import com.lethalcoders.movieapi.domain.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("")
    public ResponseEntity<Movie> create(@RequestBody Movie movie) {
        movie = movieService.create(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }
}
