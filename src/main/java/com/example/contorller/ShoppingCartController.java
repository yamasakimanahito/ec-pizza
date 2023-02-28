package com.example.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.ShoppingCartForm;
import com.example.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private HttpSession session;

	@Autowired
	private ShoppingCartService shoppingcartService;

	/**
	 * カートリスト表示.
	 * 
	 * @return カートリスト
	 */
	@GetMapping("/toCart")
	public String toCartList() {
		return "cart_list";
	}

	/**
	 * 商品登録.
	 * @param form ショッピングカートフォーム
	 * @return　toCartへリダイレクト
	 */
	@PostMapping("/insertCart")
	public String insertCart(ShoppingCartForm form) {
		User user = (User)session.getAttribute("User");
		shoppingcartService.insertCat(form, user.getId());
		return "redirect:/shoppingCart/toCart";
	}

	@PostMapping("/showCart")
	public String showCart() {
		return "cart_list";
	}

	/**
	 * 注文商品削除.
	 * 
	 * @param orderItemId 注文商品ID
	 * @return toCartへリダイレクト
	 */
	@PostMapping("/deleteCart")
	public String deleteCartItems(Integer orderItemId) {
		shoppingcartService.deleteCartContents(orderItemId);
		return "redirect:/shoppingCart/toCart";
	}

}
