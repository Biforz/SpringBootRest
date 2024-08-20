package org.example.springbootrest.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private BigDecimal budget;
}
