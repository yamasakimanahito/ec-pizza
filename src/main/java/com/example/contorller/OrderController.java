package com.example.contorller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.OrderForm;
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

	@GetMapping("/toOrderConfirm")
	public String orderConfirm(OrderForm form) {

		return "/materialize-version/order_confirm";
	}

	/**
	 * 受け取った利用者情報を登録を行います.
	 * 
	 * @param form   オーダーフォーム
	 * @param result 入力値チェック
	 * @return 注文完了画面へ
	 */
	@PostMapping("/order")
	public String order(@Validated OrderForm form, BindingResult result) {
		if (form.getDestinationEmail().equals("")) {
			result.rejectValue("destinationEmail", "", "メールアドレスを入力して下さい");
		}

		if (form.getDeliveryDate() == null) {
			result.rejectValue("deliveryDate", null, "配達日を入力してください");
		}
		

		if (result.hasErrors()) {
			return orderConfirm(form);
		}

		LocalDateTime nowLocalDateTime = LocalDateTime.now();
		nowLocalDateTime = nowLocalDateTime.plusHours(3);
		Timestamp after3TimeStamp = Timestamp.valueOf(nowLocalDateTime);

		int year = form.getDeliveryDate().toLocalDate().getYear();
		int month = form.getDeliveryDate().toLocalDate().getMonthValue();
		int day = form.getDeliveryDate().toLocalDate().getDayOfMonth();
		LocalDateTime localDateTime = LocalDateTime.of(year, month, day, form.getDeliveryTime(), 0);
		Timestamp deliveryDateTimestamp = Timestamp.valueOf(localDateTime);
		System.out.println(deliveryDateTimestamp);

		if (deliveryDateTimestamp.before(after3TimeStamp)) {
			result.rejectValue("deliveryDate", null, "今から3時間の日時を入力して下さい");
		}

		if (result.hasErrors()) {
			return orderConfirm(form);
		}

		orderService.order(form);

		return "/materialize-version/order_finished";

	}

}
