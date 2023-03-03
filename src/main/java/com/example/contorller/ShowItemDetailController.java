package com.example.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.form.ShoppingCartForm;
import com.example.service.ShowItemDetailService;

/**
 * 商品の詳細情報を操作するコントローラー.
 * 
 * @author yoshimatsushouta
 *
 */
@Controller
@RequestMapping("/ShowItemDetail")
public class ShowItemDetailController {
	@Autowired
	private ShowItemDetailService showItemDetailService;

	/**
	 * 商品詳細情報とトッピング一覧を取得し商品詳細画面へ遷移
	 * 
	 * @param itemId 商品ID
	 * @param model  モデル
	 * @return 商品詳細画面へ遷移
	 */
	@GetMapping("/ToItemDetail")
	public String showItemDetail(Integer id, Model model, ShoppingCartForm form) {
		Item item = showItemDetailService.showItemDetail(id);
		model.addAttribute("Item", item);
		return "/materialize-version/item_detail.html";
	}

}
