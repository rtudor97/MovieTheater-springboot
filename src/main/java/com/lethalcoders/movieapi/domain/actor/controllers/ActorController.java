package com.lethalcoders.movieapi.domain.actor.controllers;

import com.lethalcoders.movieapi.domain.actor.models.Actor;
import com.lethalcoders.movieapi.domain.actor.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService){
        this.actorService = actorService;
    }

    @PostMapping("")
    public ResponseEntity<Actor> create(@RequestBody Actor actor){
        actor = actorService.create(actor);
        return new ResponseEntity<>(actor, HttpStatus.CREATED);
    }

}
