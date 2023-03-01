package com.example.common;

/**
 * 購入ステータスを表すenum.
 * 
 * @author yoshimatsushouta
 *
 */
public enum StatusEnum {
	PRE_ORDER(0, "注文前"), NOT_PAYMENT(1, "未入金"), DEPOSITED(2, "入金済"), SHIPPED(3, "発送済"), COMPLETE(4, "配送完了"),
	CANCEL(9, "キャンセル");

	// 定数に含めるフィールド、コンストラクタ、メソッドを定義
	/** 現在のステータス */
	private final int statusValue;
	/** 状態名 */
	private final String statusName;

	/**
	 * コンストラクタ。
	 */
	private StatusEnum(int statusValue, String statusName) {
		this.statusValue = statusValue;
		this.statusName = statusName;

	}

	/**
	 * statusからenumを取得
	 * 
	 * @param status 番号
	 * @return 渡された番号を含むenum
	 */
	public static StatusEnum of(int status) {
		for (StatusEnum genderEnum : StatusEnum.values()) {
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
		return statusName;
	}

}
