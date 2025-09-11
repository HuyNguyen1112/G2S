package com.example.customer.mapper;

import com.example.customer.dto.OrderDTO;
import com.example.customer.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OrderMapper {
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "customerId", source = "customer.customerId")
    OrderDTO toDTO(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee.id",source = "employeeId")
    @Mapping(target = "customer.customerId", source = "customerId")
    Order toEntity(OrderDTO orderDTO);
}
