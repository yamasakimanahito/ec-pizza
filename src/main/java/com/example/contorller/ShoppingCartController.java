package com.example.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.UserInfo;
import com.example.form.ShoppingCartForm;
import com.example.service.ShoppingCartService;


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
	private ShoppingCartService shoppingcartService;

	/**
	 * 商品をカートに追加する.
	 * 
	 * @param form  ショッピングカートフォーム
	 * @param model モデル
	 * @return toCartへリダイレクト
	 */
	@PostMapping("/insertCart")
	public String insertCart(ShoppingCartForm form, Model model, @AuthenticationPrincipal LoginUser loginUser) {


		UserInfo user = loginUser.getUserInfo();
		shoppingcartService.insertCat(form, user.getId());

		return "redirect:/shoppingCart/toCart";
	}

	/**
	 * カートリストを表示.
	 * 
	 * @param form  ショッピングカートフォーム
	 * @param model モデル
	 * @return カートリスト
	 */
	@GetMapping("/toCart")
	public String toCartList(Model model, @AuthenticationPrincipal LoginUser loginUser) {

		UserInfo user = loginUser.getUserInfo();

		Order orderList = shoppingcartService.showCart(user.getId());
		model.addAttribute("order", orderList);

		return "/materialize-version/cart_list";
	}

	/**
	 * カートの中身表示.
	 * 
	 * @param form  ショッピングカートフォーム
	 * @param model モデル * @return カートリスト
	 * @return カートリスト
	 */
	@GetMapping("/showCart")
	public String showCart(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		UserInfo user = loginUser.getUserInfo();

		Order orderList = shoppingcartService.showCart(user.getId());
		model.addAttribute("order", orderList);
		return "/materialize-version/cart_list";
	}

	/**
	 * カート商品削除.
	 * 
	 * @param orderItemId 注文商品ID
	 * @return toCartへリダイレクト
	 */
	@GetMapping("/deleteCart")
	public String deleteCartItems(Integer orderItemId) {
		shoppingcartService.deleteByOrderId(orderItemId);

		return "redirect:/shoppingCart/toCart";
	}

	/**
	 * カート商品を一括削除する.
	 * 
	 * @param orderId オーダーアイテムId
	 * @return toCartへリダイレクト
	 */
	@GetMapping("/allDeleteOrderItem")
	public String allDeleteOrderItem(Integer orderId) {

		shoppingcartService.allDeleteOrderItem(orderId);
		return "redirect:/shoppingCart/toCart";
	}

}
