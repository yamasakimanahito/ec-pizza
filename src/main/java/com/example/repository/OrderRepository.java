package com.example.repository;

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
 * Orderドメインのリポジトリー.
 * @author yamasakimanahito
 *
 */
@Repository
public class OrderRepository {
	private static final RowMapper<Order> ORDER_ROW_MAPPER = new BeanPropertyRowMapper<>(Order.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;	
	
	/**
	 * 注文情報を１件検索.
	 * @param id
	 * @return　注文情報
	 */
	public Order load(Integer id) {
		String sql = "select id,user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method from orders where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Order order = template.queryForObject(sql, param,ORDER_ROW_MAPPER );
		
		return order;
	}
	
	/**
	 * 注文情報を更新
	 * @param order
	 * @return　注文更新情報
	 */
	public Order update(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "update orders set user_id=:userId,status=:status,total_price=:totalPrice,order_date=:orderDate,destination_name=:destinationName,destination_email=:destinationEmail,destination_zipcode=:destinationZipcode,destination_address=:destinationAddress,destination_tel=:destinationTel,delivery_time=:deliveryTime,payment_method=:paymentMethod where id=:id;";
		template.update(sql, param);
		return order;
	}

}
