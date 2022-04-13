package com.lethalcoders.movieapi.domain.customer.service;

import com.lethalcoders.movieapi.domain.core.interfaces.CrudService;
import com.lethalcoders.movieapi.domain.customer.exceptions.CustomerNotFoundException;
import com.lethalcoders.movieapi.domain.customer.models.Customer;

public interface CustomerService extends CrudService<Customer,Long> {
    Customer findByBudget(Double budget) throws CustomerNotFoundException;
}
