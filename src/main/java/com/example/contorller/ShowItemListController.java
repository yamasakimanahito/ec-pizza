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

@Controller
@RequestMapping("/showItem")
public class ShowItemListController {
	
	@Autowired
	private ShowItemListService showItemListService;
	
	@GetMapping("/showItemList")
	public String showItemList() {
		
		return "materialize-version/item_list";
		
	}
	@PostMapping("/findByName")
	public String findByName(String name,Model model) {
		List<Item> itemList = showItemListService.showItemList(name);
		model.addAttribute("itemList",itemList);
		return "materialize-version/item_list" ;
	}

}
