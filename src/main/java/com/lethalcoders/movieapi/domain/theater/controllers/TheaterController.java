package com.lethalcoders.movieapi.domain.theater.controllers;

import com.lethalcoders.movieapi.domain.theater.models.Theater;
import com.lethalcoders.movieapi.domain.theater.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    private TheaterService theaterService;

    @Autowired
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping("")
    public ResponseEntity<Theater> create(@RequestBody Theater theater){
        theater = theaterService.create(theater);
        return new ResponseEntity<>(theater, HttpStatus.CREATED); //201
    }
}
