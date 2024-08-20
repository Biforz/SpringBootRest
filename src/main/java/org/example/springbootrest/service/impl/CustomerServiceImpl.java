package org.example.springbootrest.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springbootrest.model.Customer;
import org.example.springbootrest.repository.CustomerRepository;
import org.example.springbootrest.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new RuntimeException("No customers found");
        }
        return customers;
    }

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() != null) {
            throw new RuntimeException("Customer already exists");
        }
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
