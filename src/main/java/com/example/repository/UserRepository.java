package com.example.repository;

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
	 * 
	 * @param password フォームから受け取ったパスワード
	 * @param email    フォームから受け取ったEメール
	 * @return 該当する顧客情報
	 * @exception org.springframework.dao.DataAccessException 従業員が存在しない場合は例外を発生します
	 */
	public User findByPasswordAndEmail(String password, String email) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);

		String sql = "SELECT id, name, email, PASSWORD, zipcode, address, telephone FROM users WHERE email = :email AND PASSWORD = :password;";

		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);

		return user;
	}
}
