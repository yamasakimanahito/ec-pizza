package com.example.domain;

import java.util.List;

/**
 * 注文商品に関するドメイン.
 * 
 * @author yoshimatsushouta
 *
 */
public class OrderItem {
	/** ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** 注文ID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** 商品サイズ */
	private Character size;
	/** 商品 */
	private Item item;
	/** 注文トッピングリスト */
	private List<OrderTopping> orderToppingList;

	/**
	 * 1商品に対する小計を取得する.
	 * 
	 * @return 小計金額
	 * @HACK 動作確認未 似たような処理が多い
	 */
	public int getSubTotal() {
// (商品(M or L) + (トッピング(M or L(商品と連動)) * トッピング数)) * 数量 
		int toppingResult = 0;

		if (this.size == 'M') {
			for (OrderTopping orderTopping : orderToppingList) {
				toppingResult += orderTopping.getTopping().getPriceM();
			}
			int itemPrice = ((item.getPriceM() + toppingResult) * this.quantity);
			return itemPrice;
		} else if (this.size == 'L') {
			for (OrderTopping orderTopping : orderToppingList) {
				toppingResult += orderTopping.getTopping().getPriceL();
			}
			int itemPrice = ((item.getPriceL() + toppingResult) * this.quantity);
			return itemPrice;
		} else {
			return 0;
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

}
