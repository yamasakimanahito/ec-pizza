package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
 * 
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
		
		//id採番
		order.setId(getPrimaryId());


		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		// インサート処理
		String insertOrder = "INSERT INTO orders( user_id, status, total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method) "
				+ " VALUES(:userId,:status,:totalPrice,:orderDate,:destinationName,:destinationEmail,:destinationZipcode,:destinationAddress,:destinationTel,:deliveryTime,:paymentMethod);";
		template.update(insertOrder, param);
		System.out.println("成功");
		return order;
	}
	
	/**
	 * 注文テーブルの中で一番大きいID + 1(プライマリーキー=主キー)を取得する.
	 * @return テーブル内で一番値が大きいID + 1.データがない場合は1
	 */
	private Integer getPrimaryId() {
		try {
			Integer maxId = template.queryForObject("SELECT MAX(id) FROM orders;", new MapSqlParameterSource(),
					Integer.class);
			return maxId + 1;
		} catch (DataAccessException e) {
			// データが存在しない場合
			return 1;
		}
	}
	/**
	 * 注文を検索します.
	 * 
	 * @param id ID
	 * @return 注文検索結果
	 */
	public Order load(Integer id) {
		String sql = "select id, user_id, status, total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method from orders  where id= :id;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Order order = template.queryForObject(sql, param, Order_ROW_MAPPER);
		System.out.println(order);
		return order;
	}

	/**
	 * ？
	 * 
	 * @param userId ユーザーID
	 * @param status 状態
	 * @return
	 */
	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		String sql = "select id, user_id, status, total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method from orders  where  user_id =:userId and status=:status ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);
		List<Order> orderList = template.query(sql, param, Order_ROW_MAPPER);
		if (orderList.size() == 0) {
			return null;
		}
		return orderList.get(0);
	}

}
