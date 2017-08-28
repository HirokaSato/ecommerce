package jp.co.rakus.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	
	/**
	 * 注文IDで商品リストを取得.
	 * 
	 * @param orderId 注文ID
	 * @return　商品リスト
	 */
	public List<OrderItem> findByOrderId(long orderId){
		String sql = "select id,item_id,order_id,quantity,size from order_items where order_id = :orderId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId",orderId);
		List<OrderItem> orderItemList = template.query(sql, param, orderItemRowmapper);
		return orderItemList;
	}
	
	/**
	 * 注文された商品を追加する.<br>
	 * by shun.nakano
	 * 
	 * @param orderItem 注文された商品情報
	 * @return 注文したかった商品
	 */
	public OrderItem insert(OrderItem orderItem){
		template.update("insert into order_items (item_id,order_id,quantity,size) values (:itemId,:orderId,:quantity,:size)", 
				new BeanPropertySqlParameterSource(orderItem));
		return orderItem;
	}
	
	/**
	 * カート内の注文商品を削除する.
	 * by rui.toguchi
	 * 
	 * @param itemId 商品ID
	 */
	public void deleteByItemId(Integer itemId){
		String sql = "delete from order_items where item_id = :itemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
		template.update(sql, param);
	}
	
}
