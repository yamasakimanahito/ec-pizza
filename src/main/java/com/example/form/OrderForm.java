package com.example.form;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.example.domain.OrderItem;
import com.example.domain.UserInfo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Orderドメインのフォーム.
 * 
 * @author yamasakimanahito
 *
 */
public class OrderForm {
	/** 注文状態 */
	private Integer status;
	/** 合計金額 */
	private Integer totalPrice;
	/** 宛名氏名 */
	@NotBlank(message = "名前を入力して下さい")
	private String destinationName;
	/** 宛先Eメール */
	@NotBlank(message = "メールアドレスを入力して下さい")
	@Email(message = "メールアドレスの形式が不正です")
	private String destinationEmail;
	/** 宛先郵便番号 */
	@NotBlank(message = "郵便番号を入力して下さい")
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号形式にしてください")
	private String destinationZipcode;
	/** 宛先住所 */
	@NotBlank(message = "住所を入力して下さい")
	private String destinationAddress;
	/** 宛先電話番号 */
	@NotBlank(message = "電話番号を入力して下さい")
	@Pattern(regexp = "^[0-9]{4}-[0-9]{4}-[0-9]{4}$", message = "電話番号はXXXX-XXXX-XXXXの形式で入力して下さい")
	private String destinationTel;
	/** 配達時間 */
	@NotBlank(message = "配達日時を入力して下さい")
	private Timestamp deliveryTime;
	/** 支払い方法 */
	private Integer paymentMethod;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "OrderForm [status=" + status + ", totalPrice=" + totalPrice + ", destinationName=" + destinationName
				+ ", destinationEmail=" + destinationEmail + ", destinationZipcode=" + destinationZipcode
				+ ", destinationAddress=" + destinationAddress + ", destinationTel=" + destinationTel
				+ ", deliveryTime=" + deliveryTime + ", paymentMethod=" + paymentMethod + "]";
	}

}
