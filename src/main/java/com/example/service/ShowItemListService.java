package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品一覧を表示するサービス.
 * 
 * @author nanakono
 *
 */
@Service
public class ShowItemListService {
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品全件一覧情報を取得します.
	 * 
	 * @return　商品情報全件リスト
	 */
	public List<Item> findAll(){
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}
	

}
