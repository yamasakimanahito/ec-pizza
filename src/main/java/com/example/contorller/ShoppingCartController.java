package com.example.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Order;
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

	/**
	 * 商品をカートに追加する.
	 * 
	 * @param form ショッピングカートフォーム
	 * @return toCartへリダイレクト
	 */
	@PostMapping("/insertCart")
	public String insertCart(ShoppingCartForm form) {
		System.out.println(form.getSize());
		System.out.println(form.getItemId());
		System.out.println(form.getQuantity());
		System.out.println(form.getToppingIdList());
		User user = (User) session.getAttribute("User");
		shoppingcartService.insertCat(form, 1);

		return "redirect:/shoppingCart/toCart";
	}

	/**
	 * カートリストを表示.
	 * 
	 * @return カートリスト
	 */
	@GetMapping("/toCart")
	public String toCartList(Model model) {
		User user = (User) session.getAttribute("User");
		List<Order> order = shoppingcartService.showCart(1);
		
		for(Order orderList : order) {
			List<OrderItem> orderItem = orderList.getOrderItemList();
				model.addAttribute("orderItemList", orderItem);
			}
		
		return "/materialize-version/cart_list";
	}

	/**
	 * カートの中身表示.
	 * 
	 * @return カートリスト
	 */
	@PostMapping("/showCart")
	public String showCart(Model model) {
		List<Order> orderList = shoppingcartService.showCart(1);
		model.addAttribute("orderList", orderList);
		return "/materialize-version/cart_list";
	}

	/**
	 * 注文商品削除.
	 * 
	 * @param orderItemId 注文商品ID
	 * @return toCartへリダイレクト
	 */
	@GetMapping("/deleteCart")
	public String deleteCartItems(Integer orderItemId) {
		System.out.println(orderItemId);
		System.out.println("mamam");
		shoppingcartService.deleteCartContents(orderItemId);
		return "redirect:/shoppingCart/toCart";
	}

}
