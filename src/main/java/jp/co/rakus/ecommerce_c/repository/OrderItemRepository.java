package jp.co.rakus.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommerce_c.domain.OrderItem;

/**
 * orderItemテーブルを操作するクラス.
 * 
 * @author rui.toguchi
 *
 */
@Repository
public class OrderItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate template; 
	
	private final static RowMapper<OrderItem> orderItemRowmapper = (rs,i) -> {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getLong("id"));
		orderItem.setItemId(rs.getLong("item_id"));
		orderItem.setOrderId(rs.getLong("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		orderItem.setSize(rs.getString("size"));
		return orderItem;
	} ;
	
}
