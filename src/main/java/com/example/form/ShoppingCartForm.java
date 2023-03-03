package com.example.form;

import java.util.List;

import jakarta.validation.constraints.NotNull;

/**
 * ショッピングカート関連フォーム.
 * 
 * @author matsumotoyuyya
 *
 */
public class ShoppingCartForm {

	/**
	 * 商品ID
	 */
	private Integer itemId;

	/**
	 * 数量
	 */
	@NotNull(message="数量を入力してください")
	private Integer quantity;

	/**
	 * サイズ
	 */
	private String size;

	/**
	 * トッピングリスト
	 */
	private List<Integer> toppingIdList;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Integer> getToppingIdList() {
		return toppingIdList;
	}

	public void setToppingIdList(List<Integer> toppingIdList) {
		this.toppingIdList = toppingIdList;
	}

	public ShoppingCartForm(Integer itemId, Integer quantity, String size, List<Integer> toppingIdList) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.size = size;
		this.toppingIdList = toppingIdList;
	}

	public ShoppingCartForm() {
		// TODO Auto-generated constructor stub
	}
}
