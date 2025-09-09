package com.example.customer.mapper;

import com.example.customer.dto.CustomerRequest;
import com.example.customer.dto.CustomerResponse;
import com.example.customer.entity.Customer;

public class CustomerMapper {
    public static CustomerResponse toResponse(Customer customer) {
        CustomerResponse dto = new CustomerResponse(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getContactName(),
                customer.getAddress(),
                customer.getCity(),
                customer.getPostalCode(),
                customer.getCountry()
        );

        return dto;
    }

    public static Customer toEntity(CustomerRequest dto) {
        Customer customer = new Customer();

        customer.setCustomerName(dto.getCustomerName());
        customer.setContactName(dto.getContactName());
        customer.setAddress(dto.getAddress());
        customer.setCity(dto.getCity());
        customer.setPostalCode(dto.getPostalCode());
        customer.setCountry(dto.getCountry());

        return customer;
    }
}
