package com.example.form;

import java.util.List;

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
	private Integer quantity;

	/**
	 * サイズ
	 */
	private String size;

	/**
	 * トッピングリスト
	 */
	private List<Integer> toppingIdLis;

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

	public List<Integer> getToppingIdLis() {
		return toppingIdLis;
	}

	public void setToppingIdLis(List<Integer> toppingIdLis) {
		this.toppingIdLis = toppingIdLis;
	}

	@Override
	public String toString() {
		return "ShoppingCartForm [userId=" + itemId + ", quantity=" + quantity + ", size=" + size + ", toppingIdLis="
				+ toppingIdLis + "]";
	}

	public ShoppingCartForm() {
	}

	public ShoppingCartForm(Integer userId, Integer quantity, String size, List<Integer> toppingIdLis) {
		super();
		this.itemId = userId;
		this.quantity = quantity;
		this.size = size;
		this.toppingIdLis = toppingIdLis;
	}

}
