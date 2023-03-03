package com.example.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.service.OrderConfirmService;

@Controller
@RequestMapping("/OrderConfirmController")
public class OrderConfirmController {
	@Autowired
	private OrderConfirmService orderConfirmService;

	@GetMapping("toOrderConfirm")
	public String Order(Integer id, Model model) {
		Order order = orderConfirmService.GetOrderId(id);
		List<OrderItem> orderItemList = order.getOrderItemList();
		for(OrderItem Item:orderItemList) {
			model.addAttribute("Item",Item);
		}
		return "/materialize-version/order_confirm";
	}

}
	