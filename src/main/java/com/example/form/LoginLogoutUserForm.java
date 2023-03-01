package com.example.form;

/**
 * 顧客ログイン画面から受け取ったフォーム
 * 
 * @author yoshimatsushouta
 *
 */
public class LoginLogoutUserForm {
	/** パスワード */
	private String password;
	/** Eメール */
	private String email;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "LoginLogoutUserForm [password=" + password + ", email=" + email + "]";
	}
}
