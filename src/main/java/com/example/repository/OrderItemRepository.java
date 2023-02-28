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

import com.example.domain.OrderItem;

/**
 * 注文商品関連レポジトリー.
 * 
 * @author matsumotoyuyya
 *
 */
@Repository
public class OrderItemRepository {

	private static final RowMapper<OrderItem> OrderItem_ROW_MAPPER = new BeanPropertyRowMapper<>(OrderItem.class);

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 注文商品を登録します.
	 * 
	 * @param orderTopping 注文商品
	 * @return インサートした注文商品
	 */
	synchronized public OrderItem insert(OrderItem orderItem) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);

		// IDの採番
				orderItem.setId(getOrderId());
		// インサート処理
		String insertSql = "INSERT INTO order_items(id,item_id,order_id,quantity,size) "
				+ " VALUES(:id,:itemId,:orderId,:quantity,:size)";
		template.update(insertSql, param);

		return orderItem;
	}

	/**
	 * 注文商品を検索します.
	 * @param orderId 注文商品ID
	 * @return 注文商品
	 */
	public OrderItem findByOrderId(Integer orderId) {
		String sql = "select id,item_id,order_id,quantity,size from order_items where order_id =:orderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
		List<OrderItem> getOrder = template.query(sql, param, OrderItem_ROW_MAPPER);
		if (getOrder.size() == 0) {
			return null;
		}
		return getOrder.get(0);
	}
	
	
	/**
	 * オーダーID生成
	 * @return
	 */
	private Integer getOrderId() {
		try {
			Integer maxId = template.queryForObject("SELECT MAX(order_id) FROM order_items;", new MapSqlParameterSource(),
					Integer.class);
			return maxId + 1;
		} catch (DataAccessException e) {
			// データが存在しない場合
			return 1;
		}
	}
	/**
	 * 注文商品を削除する.
	 * @param orderItemId  注文商品id
	 */
	public void deleteByOrderId(Integer orderItemId) {
		String deleteSql = "delete from order_items where id=:orderItemId;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderItemId);
		template.update(deleteSql, param);
	}
}
