package com.example.customer.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class OrderDTO {
    private int id;

    @NotNull(message = "Oder date is required")
    private LocalDateTime orderDate;

    private int employeeId;

    private int customerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
