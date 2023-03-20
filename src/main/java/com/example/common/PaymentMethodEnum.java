package com.example.common;

/**
 * 支払い方法を表すenum.
 * 
 * @author yoshimatsushouta
 *
 */
public enum PaymentMethodEnum {
	COD(1, "代金引換"), CREDIT_CARD(2, "クレジットカード");

	
	/** 現在のステータス */
	private final int paymentMethodValue;
	/** 支払い方法 */
	private final String paymentMethodName;

	/**
	 * コンストラクタ.
	 */
	private PaymentMethodEnum(int paymentMethodValue, String paymentMethodName) {
		this.paymentMethodValue = paymentMethodValue;
		this.paymentMethodName = paymentMethodName;

	}

	/**
	 * paymentMethodからenumを取得
	 * 
	 * @param paymentMethod 番号
	 * @return 渡された番号を含むenum
	 */
	public static PaymentMethodEnum of(int paymentMethod) {
		for (PaymentMethodEnum genderEnum : PaymentMethodEnum.values()) {
			if (genderEnum.paymentMethodValue == paymentMethod) {
				return genderEnum;
			}
		}
		throw new IndexOutOfBoundsException("The value of intelligibility doesn't exist.");
	}

	public int getStatusValue() {
		return paymentMethodValue;
	}

	public String getStatusName() {
		return paymentMethodName;
	}
}
