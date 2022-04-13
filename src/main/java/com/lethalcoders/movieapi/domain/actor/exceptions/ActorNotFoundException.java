package com.lethalcoders.movieapi.domain.actor.exceptions;

import com.lethalcoders.movieapi.domain.actor.models.Actor;

public class ActorNotFoundException extends Exception{
    public ActorNotFoundException(String msg){super (msg);}
}
