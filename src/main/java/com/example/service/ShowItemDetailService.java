package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

/**
 * 商品とトッピング情報を操作する業務処理を行うサービス.
 * 
 * @author yoshimatsushouta
 *
 */
@Service
public class ShowItemDetailService {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * 選択した商品にトッピング一覧情報を合わせて取得する.
	 * 
	 * @param itemId 選択した商品
	 * @return 選択した商品の詳細情報とトッピング一覧情報を合わせて商品詳細ページに遷移
	 */
	public Item showItemDetail(Integer itemId) {
		Item item = itemRepository.load(itemId);
		item.setToppingList(toppingRepository.findAll());
		return item;
	}
}
