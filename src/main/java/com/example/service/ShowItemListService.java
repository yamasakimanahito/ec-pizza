package com.example.service;

import java.util.List;

import org.checkerframework.checker.units.qual.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品一覧を表示するためのサービスクラス
 * @author yamasakimanahito
 *
 */
@Service
@Temperature
public class ShowItemListService {
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 名前の曖昧検索を行います.
	 * @param name
	 * @return 検索された商品情報
	 */
	public List<Item> showItemList(String name){
		List<Item> itemList = itemRepository.findByName(name);
		return itemList;
	}

}
