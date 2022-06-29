package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private List<OrderDto> orderList = new ArrayList<>();

    @PostConstruct
    public void initDb() {
        orderList.add(new OrderDto(1, "Mobile", 5000));
        orderList.add(new OrderDto(2, "Book", 200));
        orderList.add(new OrderDto(3, "Pen", 1));
        orderList.add(new OrderDto(80, "AB", 62));
    }

    public OrderDto addOrder(OrderDto orderDto) {
        orderList.add(orderDto);
        return orderDto;
    }

    public List<OrderDto> getOrders() {
        return orderList;
    }
}
