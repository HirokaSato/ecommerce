package jp.co.rakus.ecommerce_c.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
	
	private SimpleJdbcInsert insert;
	
	@PostConstruct
	public void init(){
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate)template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
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
	 * @return 追加したときに採番されたid情報を加えたOrderItem
	 */
	public OrderItem insert(OrderItem orderItem){
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		Number key = insert.executeAndReturnKey(param);
		orderItem.setId(key.intValue());
		return orderItem;
	}
	
	/**
	 * カート内の注文商品を削除する.
	 * by rui.toguchi
	 * 
	 * @param itemId 商品ID
	 */
	public void deleteByItemId(Integer id){
		String sql = "delete from order_items where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
	/**
	 * IDで注文商品を検索する.
	 * 
	 * @param id 検索対象ID
	 * @return 照合された注文商品情報、なければnull
	 */
	public OrderItem findById(long id){
		String sql = "select id,item_id,order_id,quantity,size from order_items where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		OrderItem orderItem = new OrderItem();
		try{			
			orderItem = template.queryForObject(sql, param, orderItemRowmapper);
		}catch(Exception e){
			orderItem = null;
		}
		return orderItem;
	}
	
}
