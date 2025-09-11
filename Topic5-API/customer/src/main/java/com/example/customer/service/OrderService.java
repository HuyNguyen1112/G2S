package com.example.customer.service;

import com.example.customer.dto.OrderDTO;
import com.example.customer.entity.Customer;
import com.example.customer.entity.Employee;
import com.example.customer.entity.Order;
import com.example.customer.exception.NotFoundException;
import com.example.customer.mapper.OrderMapper;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.repository.EmployeeRepository;
import com.example.customer.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        return orderMapper.toDTO(order);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);

        Employee employee = employeeRepository.findById(orderDTO.getEmployeeId())
                .orElseThrow(()->new NotFoundException(orderDTO.getEmployeeId()));

        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(()->new NotFoundException(orderDTO.getCustomerId()));

        order.setEmployee(employee);
        order.setCustomer(customer);

        return orderMapper.toDTO(orderRepository.save(order));
    }

    public OrderDTO updateOrder(int id, OrderDTO orderDTO) {
        if(!orderRepository.existsById(id)) {
            throw new NotFoundException(id);
        }

        Order order = orderMapper.toEntity(orderDTO);
        order.setId(id);

        return orderMapper.toDTO(orderRepository.save(order));
    }

    public void deleteOrder(int id) {
        if(!orderRepository.existsById(id)) {
            throw new NotFoundException(id);
        }

        orderRepository.deleteById(id);
    }

    public List<OrderDTO> getOrdersByEmployeeId(Integer employeeId) {
        List<Order> orders = orderRepository.findByEmployee_Id(employeeId);
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }
}
