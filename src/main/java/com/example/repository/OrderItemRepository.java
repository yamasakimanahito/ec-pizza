package com.example.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

	private static final RowMapper<OrderItem> ORDERITEM_RESULT_SET_EXTRACTOR = (rs, i) -> {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		orderItem.setSize(rs.getString("size"));
		return orderItem;
	};
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

		// インサート処理
		if(orderItem.getId()==null) {

		String insertSql = "INSERT INTO order_items(item_id,order_id,quantity,size) "
				+ " VALUES(:itemId,:orderId,:quantity,:size)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumnNames = {"id"};
		template.update(insertSql, param,keyHolder,keyColumnNames);
		orderItem.setId(keyHolder.getKey().intValue());
		System.out.println(keyHolder.getKey() +"が割り当てられました");
		}
		return orderItem;
	}

	

	/**
	 * 注文商品を検索します.
	 * 
	 * @param orderId 注文商品ID
	 * @return 注文商品
	 */
	public List<OrderItem> findByOrderId(Integer orderItemId) {
		String sql = "select id,item_id,order_id,quantity,size  from order_items where order_id =:orderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderItemId);
		List<OrderItem> getOrder = template.query(sql, param, ORDERITEM_RESULT_SET_EXTRACTOR);
		if (getOrder.size() == 0) {
			return null;
		}
		return getOrder;
	}

	/**
	 * 注文商品を削除する.
	 * 
	 * @param orderItemId 注文商品id
	 */
	public void deleteByOrderId(Integer orderItemId) {
		System.out.println(orderItemId);
		String deleteSql = "delete from order_items where id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderItemId);
		template.update(deleteSql, param);
		System.out.println("成功");
	}
}
