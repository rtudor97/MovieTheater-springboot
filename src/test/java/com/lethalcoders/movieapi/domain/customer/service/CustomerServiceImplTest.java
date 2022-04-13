package com.lethalcoders.movieapi.domain.customer.service;

import com.lethalcoders.movieapi.domain.customer.exceptions.CustomerNotFoundException;
import com.lethalcoders.movieapi.domain.customer.models.Customer;
import com.lethalcoders.movieapi.domain.customer.repos.CustomerRepository;
import com.lethalcoders.movieapi.domain.movie.exceptions.MovieNotFoundException;
import com.lethalcoders.movieapi.domain.movieticket.exceptions.MovieTicketNotFoundException;
import com.lethalcoders.movieapi.domain.movieticket.models.MovieTicket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImplTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    private Customer inputCustomer;
    private Customer outputCustomer;

    @BeforeEach
    public void setup(){
        inputCustomer = new Customer("Ryan","Tudor",15.00);
        outputCustomer = new Customer("Ryan","Tudor",15.00);
        outputCustomer.setId(1L);
    }

    @Test
    @DisplayName("Customer Service create - success")
    public void createMovieTicketTest(){
        BDDMockito.doReturn(outputCustomer).when(customerRepository).save(ArgumentMatchers.any());
        Customer returnedCustomer = customerService.create(inputCustomer);
        Assertions.assertNotNull(returnedCustomer);

        Assertions.assertEquals(returnedCustomer.getFirstName(), "Ryan");
    }

    @Test
    @DisplayName("Customer service get by id - success")
    public void getMovieByIdTestSuccess() throws MovieTicketNotFoundException, CustomerNotFoundException, MovieNotFoundException {
        BDDMockito.doReturn(Optional.of(inputCustomer)).when(customerRepository).findById(1L);
        Customer foundCustomer = customerService.findById(1L);

        Assertions.assertEquals(inputCustomer.toString(),foundCustomer.toString());
    }

    @Test
    @DisplayName("Customer service get by id - fail")
    public void getMovieByIdTestFail() throws MovieTicketNotFoundException {
        BDDMockito.doReturn(Optional.empty()).when(customerRepository).findById(1L);
        Assertions.assertThrows(MovieTicketNotFoundException.class, () ->{
            customerService.findById(1L);
        });
    }

    @Test
    @DisplayName("Customer service get all tasks - success")
    public void getAllMoviesTestSuccess(){
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Ryan","Tudor",10.00));
        customers.add(new Customer("Abe","Negusse",150.00));
        BDDMockito.doReturn(customers).when(customerRepository).findAll();

        Iterable<Customer> responseCustomer = customerService.findAll();

        Assertions.assertIterableEquals(customers, responseCustomer);
    }

    @Test
    @DisplayName("Customer service update - success")
    public void updateMovieTestSuccess(){
        MovieTicket expectedMovieTicketUpdate = new MovieTicket("Harry Potter 2");

        BDDMockito.doReturn(Optional.of(inputCustomer)).when(customerRepository).findById(1L);
        BDDMockito.doReturn(expectedMovieTicketUpdate).when(customerRepository).save(ArgumentMatchers.any());

        Customer actualCustomer = customerService.update(outputCustomer);

        Assertions.assertEquals(expectedMovieTicketUpdate.toString(), actualCustomer.toString());

    }

    @Test
    @DisplayName("Customer service delete task - fail")
    public void deleteMovieTicketTest() {
        BDDMockito.doReturn(Optional.empty()).when(customerRepository).findById(1L);
        Assertions.assertThrows(MovieTicketNotFoundException.class, () -> {
            customerService.delete(1L);
        });
    }
}
