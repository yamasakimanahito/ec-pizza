package com.example.contorller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderForm;
import com.example.service.OrderConfirmService;
import com.example.service.OrderService;

/**
 * 注文手順を操作するコントローラ.
 * 
 * @author yamasakimanahito
 *
 */
@Controller
@RequestMapping("/orderCon")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderConfirmService orderConfirmService;

	/**
	 * 登録画面へ遷移する.
	 * 
	 * @param form
	 * @return
	 */
	@GetMapping("/toOrderConfirm")
	public String orderConfirm(OrderForm form, Integer orderId, Model model) {
		System.out.println("おーだiD" + orderId);

		Order orderList = orderConfirmService.GetOrderId(orderId);
		System.out.println(orderList);
		model.addAttribute("order", orderList);
		System.out.println(orderList);

		return "/materialize-version/order_confirm";
	}

	/**
	 * 受け取った利用者情報を登録を行います.
	 * 
	 * @param form   オーダーフォーム
	 * @param result 入力値チェック
	 * @param id,    model
	 * @return 注文完了画面へ
	 */
	@PostMapping("/order")
	public String order(@Validated OrderForm form, BindingResult result, Model model) {
		if (form.getDestinationEmail().equals("")) {
			result.rejectValue("destinationEmail", "", "メールアドレスを入力して下さい");
		}

		if (form.getDeliveryDate() == null) {
			result.rejectValue("deliveryDate", null, "配達日を入力してください");
		}

		if (result.hasErrors()) {
			System.out.println("エラー時" + form.getIntId());

			return orderConfirm(form, form.getIntId(), model);
		}

		LocalDateTime nowLocalDateTime = LocalDateTime.now();
		nowLocalDateTime = nowLocalDateTime.plusHours(3);
		Timestamp after3TimeStamp = Timestamp.valueOf(nowLocalDateTime);

		try {
			final String yyyyMMddhh = form.getDeliveryDate() + "-" + form.getDeliveryTime();
			Date deliveryTime = new SimpleDateFormat("yyyy-MM-dd-hh").parse(yyyyMMddhh);
			Timestamp deliveryDateTimestamp = new Timestamp(deliveryTime.getTime());

			if (deliveryDateTimestamp.before(after3TimeStamp)) {

				result.rejectValue("deliveryDate", null, "今から3時間後の日時を入力して下さい");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result.hasErrors()) {
			return orderConfirm(form, form.getIntId(), model);
		}

		orderService.order(form);

		return "/materialize-version/order_finished";

	}

}
