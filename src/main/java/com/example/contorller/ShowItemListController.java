package com.example.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemListService;

/**
 * 商品の一覧情報を操作するコントローラー.
 * 
 * @author nanakono
 *
 */
@Controller
@RequestMapping("/")
public class ShowItemListController {
	@Autowired
	private ShowItemListService showItemListService;
	
	/**
	 * 商品一覧画面に遷移.
	 * 
	 * @param model　モデル
	 * @return　商品一覧画面
	 */
	@GetMapping("/showItemList")
	public String showItemList(Model model) {
		List<Item> itemList = showItemListService.findAll();
		model.addAttribute("itemList", itemList);
		return "/materialize-version/item_list";
	}
	
	
}
