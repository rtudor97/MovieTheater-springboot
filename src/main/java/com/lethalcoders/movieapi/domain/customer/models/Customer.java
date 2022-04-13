package com.lethalcoders.movieapi.domain.customer.models;

import com.lethalcoders.movieapi.domain.core.models.Person;

import javax.persistence.Entity;

@Entity
public class Customer extends Person {

    private Double budget;

    public Customer() {
    }

    public Customer(String firstName, String lastName, Double budget) {
        super(firstName, lastName);
        this.budget = budget;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

}
