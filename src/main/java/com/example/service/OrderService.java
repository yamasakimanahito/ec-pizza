package com.example.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

import org.checkerframework.checker.units.qual.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.repository.OrderRepository;

/**
 * 注文手順を操作するサービスクラス.
 * @author yamasakimanahito
 *
 */
@Service
@Temperature
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 注文情報を受け取り、利用者の登録を行います.
	 * @param form オーダーフォーム
	 * @return　注文情報
	 */
	public Order order(OrderForm form) {
		Order order = orderRepository.load(Integer.parseInt(form.getId()));
		order.setStatus(form.getStatus());
		order.setDestinationName(form.getDestinationName());
		order.setDestinationEmail(form.getDestinationEmail());
		order.setDestinationZipcode(form.getDestinationZipcode());
		order.setDestinationAddress(form.getDestinationAddress());
		order.setDestinationTel(form.getDestinationTel());
		order.setPaymentMethod(form.getPaymentMethod());
		
		int year=form.getDeliveryDate().toLocalDate().getYear();
		int month=form.getDeliveryDate().toLocalDate().getMonthValue();
		int day=form.getDeliveryDate().toLocalDate().getDayOfMonth();
		
		LocalDateTime localDateTime = LocalDateTime.of(year,month,day,form.getDeliveryTime(),0);
		order.setDeliveryTime(Timestamp.valueOf(localDateTime));
		
		orderRepository.update(order);
		
		return order;
	}

	


	

}
