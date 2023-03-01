package com.example.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemListService;

/**
 * 商品を表示させるためのコントローラ
 * @author yamasakimanahito
 *
 */
@Controller
@RequestMapping("/showItem")
public class ShowItemListController {
	
	@Autowired
	private ShowItemListService showItemListService;
	
	/**
	 * 商品一覧画面へフォワードします.
	 * @return 商品一覧画面へ
	 */
	@GetMapping("/showItemList")
	public String showItemList() {
		
		return "materialize-version/item_list";
		
	}
	/**
	 * 名前の一覧検索.
	 * @param name　商品名
	 * @param model　モデル
	 * @return　商品一覧画面へ
	 */
	@PostMapping("/findByName")
	public String findByName(String name,Model model) {
		List<Item> itemList = showItemListService.showItemList(name);
		model.addAttribute("itemList",itemList);
		return "materialize-version/item_list" ;
	}

}
