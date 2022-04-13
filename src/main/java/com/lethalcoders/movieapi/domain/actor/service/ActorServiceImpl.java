package com.lethalcoders.movieapi.domain.actor.service;

import com.lethalcoders.movieapi.domain.actor.models.Actor;
import com.lethalcoders.movieapi.domain.actor.repos.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActorServiceImpl implements ActorService{

    private ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor findByAlias(String alias) {
        return null;
    }

    @Override
    public Actor create(Actor item) {
        item = actorRepository.save(item);
        log.info("Saved item with id {}", item.getId());
        return item;
    }

    @Override
    public Actor findById(Long id) {
        return null;
    }

    @Override
    public Iterable<Actor> findAll() {
        return null;
    }

    @Override
    public Actor update(Actor item) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}