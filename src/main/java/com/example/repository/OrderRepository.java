package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;

/**
 * 注文関連レポジトリー.
 * @author matsumotoyuyya
 *
 */
@Repository
public class OrderRepository {

	private static final RowMapper<Order> Order_ROW_MAPPER = new BeanPropertyRowMapper<>(Order.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 注文商品を登録します.
	 * 
	 * @param orderTopping 注文商品
	 * @return インサートした注文商品
	 */
	synchronized public Order insert(Order order) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		// インサート処理
		String insertOrder = "INSERT INTO orders(id, user_id, status, total_price,date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method) "
				+ " VALUES(:id,:userId,:status,:totalPrice,:date,:destinationName, :destinationEmail , : destinationZipcode , : destinationAddress ,  : destinationTel , :deliveryTime , :paymentMethod)";
		template.update(insertOrder, param);

		return order;
	}

	/**
	 * 注文を検索します.
	 * @param id ID
	 * @return 注文検索結果
	 */
	public Order load(Integer id) {
		String sql = "select id, user_id, status, total_price,date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method from orders  where id= :id;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Order order = template.queryForObject(sql, param, Order_ROW_MAPPER);
		return order;
	}

	/**
	 * ？
	 * @param userId　ユーザーID
	 * @param status　状態
	 * @return
	 */
	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		String sql = "select id, user_id, status, total_price,date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method from orders  where = user_id:userId and status: status ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		List<Order> administratorList = template.query(sql, param, Order_ROW_MAPPER);
		if (administratorList.size() == 0) {
			return null;
		}
		return administratorList.get(0);
	}

}
