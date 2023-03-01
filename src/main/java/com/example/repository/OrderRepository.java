package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Order;
@Repository
public class OrderRepository {
	private static final RowMapper<Order> ORDER_ROW_MAPPER = new BeanPropertyRowMapper<>(Order.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;	
	
	public Order load(String id) {
		String sql = "select id,user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,destination_tel,delivery_time,payment_method from orders where id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Order order = template.queryForObject(sql, param,ORDER_ROW_MAPPER );
		
		return order;
	}

}
