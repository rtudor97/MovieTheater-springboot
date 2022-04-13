package com.lethalcoders.movieapi.domain.movie.service;

import com.lethalcoders.movieapi.domain.customer.exceptions.CustomerNotFoundException;
import com.lethalcoders.movieapi.domain.movie.exceptions.MovieNotFoundException;
import com.lethalcoders.movieapi.domain.movie.models.Movie;
import com.lethalcoders.movieapi.domain.movie.repos.MovieRepository;
import com.lethalcoders.movieapi.domain.movie.service.MovieService;
<<<<<<< HEAD:src/test/java/com/lethalcoders/movieapi/movie/service/MovieServiceImplTest.java
import com.lethalcoders.movieapi.domain.movieticket.exceptions.MovieTicketNotFoundException;
=======
import com.lethalcoders.movieapi.domain.theater.exceptions.TheaterNotFoundException;
>>>>>>> 8d879247d4131edc3f9fb38c161844fac74b92ca:src/test/java/com/lethalcoders/movieapi/domain/movie/service/MovieServiceImplTest.java
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MovieServiceImplTest {
    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    private Movie inputMovie;
    private Movie outputMovie;

    @BeforeEach
    public void setup(){
        inputMovie = new Movie("SpiderMan 3");
        outputMovie = new Movie("SpiderMan 3");
        outputMovie.setId(1L);
    }

    @Test
    @DisplayName("Movie Service create - success")
    public void createMovieTest(){
        BDDMockito.doReturn(outputMovie).when(movieRepository).save(ArgumentMatchers.any());
        Movie returnedMovie = movieService.create(inputMovie);
        Assertions.assertNotNull(returnedMovie);

        Assertions.assertEquals(returnedMovie.getId(), "SpiderMan 3");
    }

    @Test
    @DisplayName("Movie service get by id - success")
<<<<<<< HEAD:src/test/java/com/lethalcoders/movieapi/movie/service/MovieServiceImplTest.java
    public void getMovieByIdTestSuccess() throws MovieNotFoundException, CustomerNotFoundException, MovieTicketNotFoundException {
=======
    public void getMovieByIdTestSuccess() throws MovieNotFoundException, CustomerNotFoundException, TheaterNotFoundException {
>>>>>>> 8d879247d4131edc3f9fb38c161844fac74b92ca:src/test/java/com/lethalcoders/movieapi/domain/movie/service/MovieServiceImplTest.java
        BDDMockito.doReturn(Optional.of(inputMovie)).when(movieRepository).findById(1L);
        Movie foundMovie = movieService.findById(1L);

        Assertions.assertEquals(inputMovie.toString(),foundMovie.toString());
    }

    @Test
    @DisplayName("Movie service get by id - fail")
    public void getMovieByIdTestFail() throws MovieNotFoundException {
        BDDMockito.doReturn(Optional.empty()).when(movieRepository).findById(1L);
        Assertions.assertThrows(MovieNotFoundException.class, () ->{
            movieService.findById(1L);
        });
    }

    @Test
    @DisplayName("Movie service get all tasks - success")
    public void getAllMoviesTestSuccess(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Spectre"));
        movies.add(new Movie("Happy Feet"));
        movies.add(new Movie("Body of Lies"));
        BDDMockito.doReturn(movies).when(movieRepository).findAll();

        Iterable<Movie> responseMovie = movieService.findAll();

        Assertions.assertIterableEquals(movies, responseMovie);
    }

    @Test
    @DisplayName("Movie service update - success")
    public void updateMovieTestSuccess(){
        Movie expectedMovieUpdate = new Movie("SpiderMan No Way Home");

        BDDMockito.doReturn(Optional.of(inputMovie)).when(movieRepository).findById(1L);
        BDDMockito.doReturn(expectedMovieUpdate).when(movieRepository).save(ArgumentMatchers.any());

        Movie actualMovie = movieService.update(outputMovie);

        Assertions.assertEquals(expectedMovieUpdate.toString(), actualMovie.toString());

    }

    @Test
    @DisplayName("Movie service delete movie - fail")
    public void deleteMovieTest(){
        BDDMockito.doReturn(Optional.empty()).when(movieRepository).findById(1L);
        Assertions.assertThrows(MovieNotFoundException.class, ()->{
            movieService.delete(1L);
        });
}}
