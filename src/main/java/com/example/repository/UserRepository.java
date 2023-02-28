package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * usersテーブルを操作するリポジトリ.
 * 
 * @author yoshimatsushouta
 *
 */
@Repository
public class UserRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Userオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

	/**
	 * パスワードとEメールから顧客情報を取得します.
	 * 
	 * @param password フォームから受け取ったパスワード
	 * @param email    フォームから受け取ったEメール
	 * @return 存在しない場合はnullを返します
	 */
	public User findByPasswordAndEmail(String password, String email) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);

		String sql = "SELECT id, name, email, PASSWORD, zipcode, address, telephone FROM users WHERE email = :email AND PASSWORD = :password;";

		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
}
