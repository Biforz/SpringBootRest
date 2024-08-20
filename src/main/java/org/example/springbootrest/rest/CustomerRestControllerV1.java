package org.example.springbootrest.rest;

import lombok.RequiredArgsConstructor;
import org.example.springbootrest.dto.CustomerDto;
import org.example.springbootrest.mapper.CustomerMapper;
import org.example.springbootrest.model.Customer;
import org.example.springbootrest.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerRestControllerV1 {

    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = customerService.findById(id);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Customer> saveNewCustomer(@RequestBody @Validated CustomerDto customer) {
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer saveCustomer = customerService.save(customerMapper.toEntity(customer));
        return new ResponseEntity<>(saveCustomer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Validated Customer customer) {
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        customerService.deleteById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        if (customers == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<CustomerDto> customerDto = customers.stream().map(customerMapper::toDto).toList();
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }
}
