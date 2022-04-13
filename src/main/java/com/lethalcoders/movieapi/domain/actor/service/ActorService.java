package com.lethalcoders.movieapi.domain.actor.service;

import com.lethalcoders.movieapi.domain.actor.models.Actor;
import com.lethalcoders.movieapi.domain.core.interfaces.CrudService;

public interface ActorService extends CrudService<Actor, Long> {
    Actor findByAlias(String alias);
}
