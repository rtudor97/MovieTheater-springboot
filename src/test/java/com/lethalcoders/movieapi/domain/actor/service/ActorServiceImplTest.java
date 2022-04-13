package com.lethalcoders.movieapi.domain.actor.service;

import com.lethalcoders.movieapi.domain.actor.exceptions.ActorNotFoundException;
import com.lethalcoders.movieapi.domain.actor.models.Actor;
import com.lethalcoders.movieapi.domain.actor.repos.ActorRepository;
import com.lethalcoders.movieapi.domain.customer.exceptions.CustomerNotFoundException;
import com.lethalcoders.movieapi.domain.movie.exceptions.MovieNotFoundException;
import com.lethalcoders.movieapi.domain.movie.models.Movie;
import com.lethalcoders.movieapi.domain.theater.exceptions.TheaterNotFoundException;
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
public class ActorServiceImplTest {

        @MockBean
        private ActorRepository actorRepository;

        @Autowired
        private ActorService actorService;

        private Actor inputActor;
        private Actor outputActor;

        @BeforeEach
        public void setup(){
            inputActor = new Actor("Michael","Jordan","Michael B. Jordan");
            outputActor = new Actor("Michael","Jordan","Michael B. Jordan");
            outputActor.setId(1L);
        }

        @Test
        @DisplayName("Actor Service create - success")
        public void createActorTest(){
            BDDMockito.doReturn(outputActor).when(actorRepository).save(ArgumentMatchers.any());
            Actor returnedActor = actorService.create(inputActor);
            Assertions.assertNotNull(returnedActor);

            Assertions.assertEquals(returnedActor.getAlias(), "Michael B. Jordan");
        }

        @Test
        @DisplayName("Actor service get by id - success")
        public void getActorByIdTestSuccess() throws TheaterNotFoundException, CustomerNotFoundException, MovieNotFoundException {
            BDDMockito.doReturn(Optional.of(inputActor)).when(actorRepository).findById(1L);
            Actor foundActor = actorService.findById(1L);

            Assertions.assertEquals(inputActor.toString(),foundActor.toString());
        }

        @Test
        @DisplayName("Actor service get by id - fail")
        public void getActorByAliasTestFail() {
            BDDMockito.doReturn(Optional.empty()).when(actorRepository).findById(1L);
            Assertions.assertThrows(ActorNotFoundException.class, () -> {
                actorService.findById(1L);
            });
        }

        @Test
        @DisplayName("Actor service get all tasks - success")
        public void getAllActorTestSuccess(){
            List<Actor> actor = new ArrayList<>();
            actor.add(new Actor("Michael B. Jordan"));
            actor.add(new Actor("Will Smith"));
            actor.add(new Actor("Kevin Bacon"));
            BDDMockito.doReturn(actor).when(actorRepository).findAll();

            Iterable<Actor> responseActor = actorService.findAll();

            Assertions.assertIterableEquals(actor, responseActor);
        }

        @Test
        @DisplayName("Actor service update - success")
        public void updateActorTestSuccess() {
            Actor expectedActorUpdate = new Actor("");

            BDDMockito.doReturn(Optional.of(inputActor)).when(actorRepository).findById(1L);
            BDDMockito.doReturn(expectedActorUpdate).when(actorRepository).save(ArgumentMatchers.any());

            Actor actualActor = actorService.update(outputActor);

            Assertions.assertEquals(expectedActorUpdate.toString(), actualActor.toString());

        }

        @Test
        @DisplayName("Actor service delete movie - fail")
        public void deleteActorTest(){
            BDDMockito.doReturn(Optional.empty()).when(actorRepository).findById(1L);
            Assertions.assertThrows(ActorNotFoundException.class, ()->{
                actorService.delete(1L);
            });
    }
}
