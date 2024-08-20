package org.example.springbootrest.mapper;

import org.example.springbootrest.dto.CustomerDto;
import org.example.springbootrest.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .address(customer.getAddress())
                .budget(customer.getBudget())
                .build();
    }

    public Customer toEntity(CustomerDto customerDto) {
        return Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .address(customerDto.getAddress())
                .budget(customerDto.getBudget())
                .build();
    }
}
