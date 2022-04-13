package com.lethalcoders.movieapi.domain.movieticket.models;

import com.lethalcoders.movieapi.domain.core.models.BaseClass;

import javax.persistence.Entity;

@Entity
public class MovieTicket extends BaseClass {

    private String name;
    private Long id;

    public MovieTicket() {
    }

    public MovieTicket(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public MovieTicket(String name) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MovieTicket{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

}
