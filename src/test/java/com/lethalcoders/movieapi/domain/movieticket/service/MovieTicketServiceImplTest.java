package com.lethalcoders.movieapi.domain.movieticket.service;
import com.lethalcoders.movieapi.domain.customer.exceptions.CustomerNotFoundException;
import com.lethalcoders.movieapi.domain.movie.exceptions.MovieNotFoundException;
import com.lethalcoders.movieapi.domain.movieticket.exceptions.MovieTicketNotFoundException;
import com.lethalcoders.movieapi.domain.movieticket.models.MovieTicket;
import com.lethalcoders.movieapi.domain.movieticket.repos.MovieTicketRepository;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MovieTicketServiceImplTest {

    @MockBean
    private MovieTicketRepository movieTicketRepository;

    @Autowired
    private MovieTicketService movieTicketService;

    private MovieTicket inputMovieTicket;
    private MovieTicket outputMovieTicket;

    @BeforeEach
    public void setup(){
        inputMovieTicket = new MovieTicket("Spider-Man");
        outputMovieTicket = new MovieTicket("Spider-Man");
        outputMovieTicket.setId(1L);
    }

    @Test
    @DisplayName("Movie Service create - success")
    public void createMovieTicketTest(){
        BDDMockito.doReturn(outputMovieTicket).when(movieTicketRepository).save(ArgumentMatchers.any());
        MovieTicket returnedMovie = movieTicketService.create(inputMovieTicket);
        Assertions.assertNotNull(returnedMovie);

        Assertions.assertEquals(returnedMovie.getName(), "Spider-Man");
    }

    @Test
    @DisplayName("Movie service get by id - success")
    public void getMovieByIdTestSuccess() throws MovieTicketNotFoundException, CustomerNotFoundException, MovieNotFoundException {
        BDDMockito.doReturn(Optional.of(inputMovieTicket)).when(movieTicketRepository).findById(1L);
        MovieTicket foundMovieTicket = movieTicketService.findById(1L);

        Assertions.assertEquals(inputMovieTicket.toString(),foundMovieTicket.toString());
    }

    @Test
    @DisplayName("Movie service get by id - fail")
    public void getMovieByIdTestFail() throws MovieTicketNotFoundException {
        BDDMockito.doReturn(Optional.empty()).when(movieTicketRepository).findById(1L);
        Assertions.assertThrows(MovieTicketNotFoundException.class, () ->{
            movieTicketService.findById(1L);
        });
    }

    @Test
    @DisplayName("Movie service get all tasks - success")
    public void getAllMoviesTestSuccess(){
        List<MovieTicket> movies = new ArrayList<>();
        movies.add(new MovieTicket("Black Panther"));
        movies.add(new MovieTicket("Kong vs Godzilla"));
        movies.add(new MovieTicket("Static Shock"));
        BDDMockito.doReturn(movies).when(movieTicketRepository).findAll();

        Iterable<MovieTicket> responseMovieTicket = movieTicketService.findAll();

        Assertions.assertIterableEquals(movies, responseMovieTicket);
    }

    @Test
    @DisplayName("Movie service update - success")
    public void updateMovieTestSuccess(){
        MovieTicket expectedMovieTicketUpdate = new MovieTicket("Harry Potter 2");

        BDDMockito.doReturn(Optional.of(inputMovieTicket)).when(movieTicketRepository).findById(1L);
        BDDMockito.doReturn(expectedMovieTicketUpdate).when(movieTicketRepository).save(ArgumentMatchers.any());

        MovieTicket actualMovie = movieTicketService.update(outputMovieTicket);

        Assertions.assertEquals(expectedMovieTicketUpdate.toString(), actualMovie.toString());

    }

    @Test
    @DisplayName("Task service delete task - fail")
    public void deleteMovieTicketTest() {
        BDDMockito.doReturn(Optional.empty()).when(movieTicketRepository).findById(1L);
        Assertions.assertThrows(MovieTicketNotFoundException.class, () -> {
            movieTicketService.delete(1L);
        });
    }
}

