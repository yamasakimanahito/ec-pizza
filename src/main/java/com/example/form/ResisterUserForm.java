package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * ユーザー情報登録時に使用するフォーム.
 * 
 * @author nanakono
 *
 */
public class ResisterUserForm {
	/** 名字 */
	@NotBlank(message = "名字を入力して下さい")
	private String lastName;
	/** 名前 */
	@NotBlank(message = "名前を入力して下さい")
	private String firstName;
	/** Eメール */
	@Pattern(regexp = "^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)$", message = "メールアドレスを入力して下さい")
	private String email;
	/** パスワード */
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$", message = "パスワードは8文字以上16文字以内で入力して下さい")
	private String password;
	/** 郵便番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号はXXX-XXXXの形式で入力して下さい")
	private String zipcode;
	/** 住所 */
	@NotBlank(message = "住所を入力して下さい")
	private String address;
	/** 電話番号 */
	@Pattern(regexp = "^[0-9]{2,4}-[0-9]{2,4}-[0-9]{3,4}$", message = "電話番号はXXXX-XXXX-XXXXの形式で入力して下さい")
	private String telephone;
	/** 確認用パスワード */
	private String confirmationPassword;

	public String getName() {
		return getLastName() + getFirstName();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

	@Override
	public String toString() {
		return "ResisterUserForm [lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ ", password=" + password + ", zipcode=" + zipcode + ", address=" + address + ", telephone="
				+ telephone + ", confirmPassword=" + confirmationPassword + "]";
	}

}
