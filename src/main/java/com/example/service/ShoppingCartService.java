package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.ShoppingCartForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

/**
 * カート関連サービス.
 * 
 * @author matsumotoyuyya
 *
 */

@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderitemRepository;

	/**
	 * 注文情報インサート業務処理.
	 * 
	 * @param form   ショッピングカートフォーム
	 * @param userId ユーザーID
	 */
	public void insertCat(ShoppingCartForm form, Integer userId) {
		Order orderStatus = orderRepository.findByUserIdAndStatus(userId, 0);
		if (orderStatus == null) {

			// 注文
			Order order = new Order();
			order.setUserId(userId);
			order.setStatus(0);
			Order orderInfo = orderRepository.insert(order);

			// 注文商品
			OrderItem orderItem = new OrderItem();
			BeanUtils.copyProperties(form, orderItem);
			orderItem.setOrderId(orderInfo.getId());

			OrderItem orderItemInfo = orderitemRepository.insert(orderItem);

			// 注文トッピング
			OrderTopping orderTopping = new OrderTopping();
			for (Integer topping : form.getToppingIdLis()) {
				orderTopping.setOrderItemId(orderItemInfo.getId());
				orderTopping.setToppingId(topping);
				orderToppingRepository.insert(orderTopping);
			}
		}

	}

	/**
	 * 注文商品削除.
	 * 
	 * @param orderItemId 注文商品ID
	 */
	public void deleteCartContents(Integer orderItemId) {
		orderitemRepository.deleteByOrderId(orderItemId);
	}

	public OrderItem showCart() {
		OrderItem orderItem = new OrderItem();
		OrderItem orderItemList = orderitemRepository.findByOrderId(orderItem.getOrderId());
		return orderItemList;

	}
}
