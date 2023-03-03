package com.example.service;

import org.checkerframework.checker.units.qual.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

@Service
@Temperature
public class OrderConfirmService {
	@Autowired
	private OrderRepository orderRepository;
	
	
	public Order GetOrderId(Integer id) {
		Order order = orderRepository.load(id);
		return order;
	}

}
