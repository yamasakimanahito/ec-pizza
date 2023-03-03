package com.example.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoginUser extends User {

	private static final long serialVersionUID = 1L;
	/** 管理者情報 */
	private final UserInfo userInfo;

	public LoginUser(UserInfo userInfo, Collection<GrantedAuthority> authorityList) {

		super(userInfo.getEmail(), userInfo.getPassword(), authorityList);
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

}
