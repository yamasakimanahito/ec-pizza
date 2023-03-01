package com.example.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.OrderItem;
import com.example.domain.User;
import com.example.form.ShoppingCartForm;
import com.example.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

/**
 * カート関連コントローラー.
 * 
 * @author matsumotoyuyya
 *
 */
@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

	@Autowired
	private HttpSession session;

	@Autowired
	private ShoppingCartService shoppingcartService;

	
	@GetMapping("/itemDetail")
	public String detail(ShoppingCartForm form) {
		return "/materialize-version/item_detail";
	}
	
	/**
	 * 商品をカートに追加する.
	 * 
	 * @param form ショッピングカートフォーム
	 * @return toCartへリダイレクト
	 */
	@PostMapping("/insertCart")
	public String insertCart(ShoppingCartForm form) {
		User user = (User) session.getAttribute("User");
		form.setItemId(1);
		form.setQuantity(3);
		form.setSize("M");
		shoppingcartService.insertCat(form, 1);
		System.out.println("1");
		OrderItem orderItemList = shoppingcartService.showCart();
		System.out.println("2");

		session.setAttribute("orderItemList", orderItemList);

		return "redirect:/shoppingCart/toCart";
	}

	/**
	 * カートリストを表示.
	 * 
	 * @return カートリスト
	 */
	@GetMapping("/toCart")
	public String toCartList() {
		return "/materialize-version/cart_list";
	}

	/**
	 * カートの中身表示.
	 * 
	 * @return カートリスト
	 */
	@PostMapping("/showCart")
	public String showCart() {
		OrderItem orderItemList = shoppingcartService.showCart();
		session.setAttribute("orderItemList", orderItemList);
		return "/materialize-version/cart_list";
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
