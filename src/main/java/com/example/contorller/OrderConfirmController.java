package com.example.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.service.OrderConfirmService;
import com.example.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/OrderConfirm")
public class OrderConfirmController {
	@Autowired
	private HttpSession session;
	@Autowired
	private ShoppingCartService shoppingcartService;
	
	

	@PostMapping("/toOrderConfirm")
	public String Order(Integer id, Model model) {
		User user = (User) session.getAttribute("User");

		Order orderList = shoppingcartService.showCart(id);

		model.addAttribute("order", orderList);
		model.addAttribute(user);
		System.out.println(orderList);

		
		
		return "/materialize-version/order_confirm";
	}

}
	