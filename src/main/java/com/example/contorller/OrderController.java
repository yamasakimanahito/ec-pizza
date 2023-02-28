package com.example.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.service.OrderConfirmService;



@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderConfirmService orderConfirmService;
	
	@GetMapping("")
	public String orderConfirm(String OrderId,Model model) {
		Order order = orderConfirmService.GetOrderId(OrderId);
		model.addAttribute("order",order);
		return "order_confirm";
	}

}
