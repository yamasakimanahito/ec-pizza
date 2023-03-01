package com.example.service;

import java.util.List;

import org.checkerframework.checker.units.qual.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

@Service
@Temperature
public class ShowItemListService {
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> showItemList(String name){
		List<Item> itemList = itemRepository.findByName(name);
		return itemList;
	}

}
