package com.lethalcoders.movieapi.domain.customer.service;

import com.lethalcoders.movieapi.domain.customer.exceptions.CustomerNotFoundException;
import com.lethalcoders.movieapi.domain.customer.models.Customer;
import com.lethalcoders.movieapi.domain.customer.repos.CustomerRepository;
import com.lethalcoders.movieapi.domain.movie.exceptions.MovieNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findByBudget(Double budget) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = customerRepository.findByBudget(budget);
        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException("Customer with said budget not found: " + budget);
        }
        return customerOptional.get();
    }

    @Override
    public Customer create(Customer item) {
        return customerRepository.save(item);
    }

    @Override
    public Customer findById(Long id) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException("No customer with id: " + id);
        }
        return customerOptional.get();
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Customer item) {
        item.setId(item.getId());
        return customerRepository.save(item);
    }

    @Override
    public void delete(Long id) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException("No customer with id: " + id);
        }
        Customer customerToRemove = customerOptional.get();
        customerRepository.delete(customerToRemove);
    }

}
