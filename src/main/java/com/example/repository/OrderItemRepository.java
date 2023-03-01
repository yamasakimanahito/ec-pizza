package com.example.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;

/**
 * 注文商品関連レポジトリー.
 * 
 * @author matsumotoyuyya
 *
 */
@Repository
public class OrderItemRepository {

	private static final ResultSetExtractor<List<OrderItem>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
		// 記事一覧が入るarticleListを生成
		List<OrderItem> orderItemList = new LinkedList<OrderItem>();
		List<OrderTopping> orderToppingList = null;

		// 前の行の記事IDを退避しておく変数
		long beforeorderItemId = 0;

		while (rs.next()) {
			// 現在検索されている記事IDを退避
			int nowOrderItemId = rs.getInt("id");

			// 現在の記事IDと前の記事IDが違う場合はArticleオブジェクトを生成
			if (nowOrderItemId != beforeorderItemId) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(nowOrderItemId);
				orderItem.setItemId(rs.getInt("item_id"));
				orderItem.setOrderId(rs.getInt("order_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setSize(rs.getString("size"));
				// 空のコメントリストを作成しArticleオブジェクトにセットしておく
				orderToppingList = new ArrayList<OrderTopping>();
				orderItem.setOrderToppingList(orderToppingList);
				// コメントがセットされていない状態のArticleオブジェクトをarticleListオブジェクトにadd
				orderItemList.add(orderItem);
			}

			// 記事だけあってコメントがない場合はCommentオブジェクトは作らない
			if (rs.getInt("com_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("ot_id"));
				orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));
				orderTopping.setToppingId(rs.getInt("ot_topping_id"));

				// コメントをarticleオブジェクト内にセットされているcommentListに直接addしている(参照型なのでこのようなことができる)
				orderToppingList.add(orderTopping);
			}

			// 現在の記事IDを前の記事IDを入れる退避IDに格納
			beforeorderItemId = nowOrderItemId;
		}
		return orderItemList;
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

		// id採番
		orderItem.setId(getPrimaryId());

		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);

		// インサート処理
		String insertSql = "INSERT INTO order_items(item_id,order_id,quantity,size) "
				+ " VALUES(:itemId,:orderId,:quantity,:size)";
		template.update(insertSql, param);
		System.out.println("成功2");
		return orderItem;
	}

	/**
	 * 注文テーブルの中で一番大きいID + 1(プライマリーキー=主キー)を取得する.
	 * 
	 * @return テーブル内で一番値が大きいID + 1.データがない場合は1
	 */
	private Integer getPrimaryId() {
		try {
			Integer maxId = template.queryForObject("SELECT MAX(id) FROM order_items;", new MapSqlParameterSource(),
					Integer.class);
			return maxId + 1;
		} catch (DataAccessException e) {
			// データが存在しない場合
			return 1;
		}
	}

	/**
	 * 注文商品を検索します.
	 * 
	 * @param orderId 注文商品ID
	 * @return 注文商品
	 */
	public OrderItem findByOrderId(Integer orderItemId) {
		String sql = "select oi.id,oi.item_id,oi.order_id,oi.quantity,oi.size , ot.id , ot.order_item_id , ot.topping_id  from order_items oi join order_toppings ot on oi.id = ot.order_item_id  where order_item_id =:orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		List<OrderItem> getOrder = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
		if (getOrder.size() == 0) {
			return null;
		}
		return getOrder.get(0);
	}

	/**
	 * 注文商品を削除する.
	 * 
	 * @param orderItemId 注文商品id
	 */
	public void deleteByOrderId(Integer orderItemId) {
		String deleteSql = "delete from order_items where id=:orderItemId;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderItemId);
		template.update(deleteSql, param);
	}
}
