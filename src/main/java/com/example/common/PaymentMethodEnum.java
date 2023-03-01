package com.example.common;

public enum PaymentMethodEnum {
	COD(1, "代金引換"), CREDIT_CARD(2, "クレジットカード");

	// 定数に含めるフィールド、コンストラクタ、メソッドを定義
	/** 現在のステータス */
	private final int statusValue;
	/** 支払い方法 */
	private final String paymentMethodName;

	/**
	 * コンストラクタ。
	 */
	private PaymentMethodEnum(int statusValue, String paymentMethodName) {
		this.statusValue = statusValue;
		this.paymentMethodName = paymentMethodName;

	}

	/**
	 * statusからenumを取得
	 * 
	 * @param status 番号
	 * @return 渡された番号を含むenum
	 */
	public static PaymentMethodEnum of(int status) {
		for (PaymentMethodEnum genderEnum : PaymentMethodEnum.values()) {
			if (genderEnum.statusValue == status) {
				return genderEnum;
			}
		}
		throw new IndexOutOfBoundsException("The value of intelligibility doesn't exist.");
	}

	public int getStatusValue() {
		return statusValue;
	}

	public String getStatusName() {
		return paymentMethodName;
	}
}
