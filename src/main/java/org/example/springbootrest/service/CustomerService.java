package org.example.springbootrest.service;

import org.example.springbootrest.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer findById(Long id);

    List<Customer> findAll();

    Customer save(Customer customer);

    void deleteById(Long id);
}
