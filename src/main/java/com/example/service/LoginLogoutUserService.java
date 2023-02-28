package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.form.LoginLogoutUserForm;
import com.example.repository.UserRepository;

/**
 * 顧客情報を操作するサービス.
 * 
 * @author yoshimatsushouta
 *
 */
@Service
@Transactional
public class LoginLogoutUserService {
	@Autowired
	private UserRepository userReposiory;

	/**
	 * 顧客のログインを行います.
	 * 
	 * @param form フォーム
	 * @return 該当する顧客情報を返します. 従業員が存在しない場合はnullを返します。
	 * 
	 */
	public User login(LoginLogoutUserForm form) {
		User user = userReposiory.findByPasswordAndEmail(form.getPassword(), form.getEmail());
		return user;
	}
}
