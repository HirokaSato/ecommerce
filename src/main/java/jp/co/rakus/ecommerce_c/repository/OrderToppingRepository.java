package jp.co.rakus.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommerce_c.domain.OrderTopping;
import jp.co.rakus.ecommerce_c.domain.Topping;

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
	
	/**
	 * 注文商品IDからトッピングリストを取得.
	 * 
	 * @param orderItemId 注文商品ID
	 * @return　注文商品に紐づけられたトッピングリスト
	 */
	public List<OrderTopping> findByfindByOrderItemId(Integer orderItemId){
		String sql = "select id, topping_id, order_item_id where order_item_id = :orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		List<OrderTopping> toppingList = template.query(sql, param,orderToppingRowMapper);
		return toppingList;
	}
  }
