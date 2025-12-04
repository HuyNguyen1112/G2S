package com.example.JWTDemo;


import com.example.JWTDemo.dto.CustomerRequest;
import com.example.JWTDemo.dto.CustomerResponse;
import com.example.JWTDemo.entity.Customer;
import com.example.JWTDemo.exception.ResourceNotFoundException;
import com.example.JWTDemo.mapper.CustomerMapper;
import com.example.JWTDemo.repository.CustomerRepository;
import com.example.JWTDemo.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    private Customer customerAlice;
    private Customer customerBob;
    private CustomerResponse resAlice;
    private CustomerResponse resBob;
    private CustomerRequest reqAlice;
    private CustomerRequest reqBob;

    @BeforeEach
    void setUp() {
        customerAlice = new Customer();
        customerAlice.setCustomerId(1);
        customerAlice.setCustomerName("Alice");
        customerAlice.setContactName("Nguyen");
        customerAlice.setAddress("123 Street");
        customerAlice.setCity("Hanoi");
        customerAlice.setPostalCode("ABC123");
        customerAlice.setCountry("VN");

        customerBob = new Customer();
        customerBob.setCustomerId(2);
        customerBob.setCustomerName("Bob");
        customerBob.setContactName("Tran");
        customerBob.setAddress("456 Avenue");
        customerBob.setCity("HCM");
        customerBob.setPostalCode("XYZ789");
        customerBob.setCountry("VN");

        resAlice = new CustomerResponse(1,"Alice", "Nguyen", "123 Street", "Hanoi", "ABC123", "VN");
        resBob = new CustomerResponse(2,"Bob", "Tran", "456 Avenue", "HCM", "XYZ789", "VN");
        reqAlice = new CustomerRequest("Alice", "Nguyen", "123 Street", "Hanoi", "ABC123", "VN");
        reqBob = new CustomerRequest("Bob", "Tran", "456 Avenue", "HCM", "XYZ789", "VN");
    }

    // ================= getAllCustomers =================

    @Test
    void getAllCustomers_returnList_whenCustomersExist() {
        // Arrange
        when(customerRepository.findAll()).thenReturn(List.of(customerAlice, customerBob));
        when(customerMapper.toResponse(customerAlice)).thenReturn(resAlice);
        when(customerMapper.toResponse(customerBob)).thenReturn(resBob);

        // Act
        List<CustomerResponse> actual = customerService.getAllCustomers();

        // Assert
        assertThat(actual).hasSize(2);
        assertThat(actual.get(0).getCustomerName()).isEqualTo("Alice");
        assertThat(actual.get(1).getContactName()).isEqualTo("Tran");

        verify(customerRepository).findAll();
        verify(customerMapper, times(2)).toResponse(any(Customer.class));
        verifyNoMoreInteractions(customerRepository, customerMapper);
    }

    // ================= getCustomerById =================

    @Test
    void getCustomerById_ReturnDTO_whenFound() {
        // Arrange
        when(customerRepository.findById(1)).thenReturn(Optional.of(customerAlice));
        when(customerMapper.toResponse(customerAlice)).thenReturn(resAlice);

        // Act
        CustomerResponse actual = customerService.getCustomerById(1);

        // Assert
        assertThat(actual.getId()).isEqualTo(1);
        assertThat(actual.getCustomerName()).isEqualTo("Alice");

        verify(customerRepository).findById(1);
        verify(customerMapper).toResponse(customerAlice);
        verifyNoMoreInteractions(customerRepository, customerMapper);
    }

    @Test
    void getCustomerById_ThrowNotFound_whenCustomerIsMissing() {
        // Arrange
        when(customerRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> customerService.getCustomerById(999))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Customer not found");

        verify(customerRepository).findById(999);
        verifyNoMoreInteractions(customerRepository);
    }

    // ================= createCustomer =================

    @Test
    void createCustomer_ReturnDTO_whenValid() {
        // Arrange
        when(customerMapper.toEntity(reqAlice)).thenReturn(customerAlice);
        when(customerRepository.save(customerAlice)).thenReturn(customerAlice);
        when(customerMapper.toResponse(customerAlice)).thenReturn(resAlice);

        // Act
        CustomerResponse actual = customerService.createCustomer(reqAlice);

        // Assert
        assertThat(actual.getCustomerName()).isEqualTo("Alice");

        verify(customerMapper).toEntity(reqAlice);
        verify(customerRepository).save(customerAlice);
        verify(customerMapper).toResponse(customerAlice);
        verifyNoMoreInteractions(customerRepository, customerMapper);
    }
}
