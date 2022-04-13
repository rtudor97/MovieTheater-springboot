package com.lethalcoders.movieapi.domain.customer.repos;

import com.lethalcoders.movieapi.domain.customer.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Optional<Customer> findByBudget(Double budget);
}
