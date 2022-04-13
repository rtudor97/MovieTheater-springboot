package com.lethalcoders.movieapi.domain.theater.service;

import com.lethalcoders.movieapi.domain.customer.exceptions.CustomerNotFoundException;
import com.lethalcoders.movieapi.domain.movie.exceptions.MovieNotFoundException;
import com.lethalcoders.movieapi.domain.movie.models.Movie;
import com.lethalcoders.movieapi.domain.theater.exceptions.TheaterNotFoundException;
import com.lethalcoders.movieapi.domain.theater.models.Theater;
import com.lethalcoders.movieapi.domain.theater.repos.TheaterRepository;
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
public class TheaterServiceTest {

    @MockBean
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterService theaterService;

    private Theater inputTheater;
    private Theater outputTheater;
    private Movie movie;

    @BeforeEach
    public void setup(){
        movie = new Movie("Casino Royale");
        inputTheater = new Theater(movie,40);
        outputTheater = new Theater();
        outputTheater.setId(1L);
    }

    @Test
    @DisplayName("Theater Service create - success")
    public void createMovieTest(){
        BDDMockito.doReturn(outputTheater).when(theaterRepository).save(ArgumentMatchers.any());
        Theater returnedTheater = theaterService.create(inputTheater);
        Assertions.assertNotNull(returnedTheater);

        Assertions.assertEquals(returnedTheater.getMovie());
    }

    @Test
    @DisplayName("Theater service get by id - success")
    public void getTheaterByIdTestSuccess() throws TheaterNotFoundException, CustomerNotFoundException, MovieNotFoundException {
        BDDMockito.doReturn(Optional.of(inputTheater)).when(theaterRepository).findById(1L);
        Theater foundTheater = theaterService.findById(1L);

        Assertions.assertEquals(inputTheater.toString(), foundTheater.toString());
    }

    @Test
    @DisplayName("Theater service get all tasks - success")
    public void getAllTheaterSuccess(){
        List<Theater> theater = new ArrayList<>();
        theater.add(new Theater());
        BDDMockito.doReturn(theater).when(theaterRepository).findAll();

        Iterable<Theater> responseTheater = theaterService.findAll();

        Assertions.assertIterableEquals(theater, responseTheater);

    }

    @Test
    @DisplayName("Theater service update - success")
    public void updateTheaterTestSuccess(){
        Theater expectedTheaterUpdate = new Theater();

        BDDMockito.doReturn(Optional.of(inputTheater)).when(theaterRepository).findById(1L);
        BDDMockito.doReturn(expectedTheaterUpdate).when(theaterRepository).save(ArgumentMatchers.any());

        Theater actualTheater = theaterService.update(outputTheater);

        Assertions.assertEquals(expectedTheaterUpdate.toString(), actualTheater.toString());
    }


    @Test
    @DisplayName("Theater service delete theater - fail")
    public void deleteTheaterTest(){
        BDDMockito.doReturn(Optional.empty()).when(theaterRepository).findById(1L);
        Assertions.assertThrows(TheaterNotFoundException.class, ()->{
            theaterService.delete(1L);
        });
    }
}
