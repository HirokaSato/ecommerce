package jp.co.rakus.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommerce_c.domain.OrderTopping;

/**
 * order_toppingsテーブルを操作するクラス.
 * 
 * @author rui.toguchi
 *
 */
@Repository
public class OrderToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final static RowMapper<OrderTopping> orderToppingRowMapper = (rs,i) -> {
		OrderTopping orderTopping = new OrderTopping();
		orderTopping.setId(rs.getInt("id"));
		orderTopping.setToppingId(rs.getInt("topping_id"));
		orderTopping.setOrderItemId(rs.getInt("order_item_id"));
		return orderTopping;
	};
  }
