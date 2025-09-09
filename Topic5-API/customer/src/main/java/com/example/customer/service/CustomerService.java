package com.example.customer.service;

import com.example.customer.dto.CustomerRequest;
import com.example.customer.dto.CustomerResponse;
import com.example.customer.entity.Customer;
import com.example.customer.exception.NotFoundException;
import com.example.customer.mapper.CustomerMapper;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(CustomerMapper::toResponse);
    }

    public CustomerResponse getCustomerById(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return CustomerMapper.toResponse(customer);
    }

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = CustomerMapper.toEntity(customerRequest);
        Customer createdCustomer = customerRepository.save(customer);
        return CustomerMapper.toResponse(createdCustomer);
    }

    public CustomerResponse updateCustomer(int id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setContactName(customerRequest.getContactName());
        customer.setAddress(customerRequest.getAddress());
        customer.setCity(customerRequest.getCity());
        customer.setPostalCode(customerRequest.getPostalCode());
        customer.setCountry(customerRequest.getCountry());

        Customer updatedCustomer = customerRepository.save(customer);
        return CustomerMapper.toResponse(updatedCustomer);
    }

    public void deleteCustomer(int id) {
        if (!customerRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        customerRepository.deleteById(id);
    }}
