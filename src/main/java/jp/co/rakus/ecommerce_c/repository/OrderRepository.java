package jp.co.rakus.ecommerce_c.repository;


import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommerce_c.domain.Order;

/**
 * 注文リポジトリ
 * @author atsuko.yoshino
 *
 */
@Repository
public class OrderRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private SimpleJdbcInsert insert;
	
	@PostConstruct
	public void init(){
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate)template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	public static final RowMapper<Order> orderRowMapper=(rs,i)->{
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));
		
		return order;
	};
	
	/**
	 * 注文内容をIdごとに注文データを取得.
	 * @param id 注文Id
	 * @return　取得した注文データ
	 */
	public List<Order> findbyId(long id,Integer status){
		String sql = "select id, user_id, status, total_price, order_date, destination_name,"
				+ "destination_email, destination_zipcode, destination_address, destination_tel,"
				+ "delivery_time, payment_method "
				+ "from orders where id = :id and status=:status order by id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id).addValue("status", status);
		try{
			List<Order> orderList = template.query(sql, param, orderRowMapper);
			return orderList;
		}catch(EmptyResultDataAccessException e){
			
			return null;
		}
	}
	
	/**
	 * 注文情報をDBに追加、または更新する.
	 * by shun.nakano
	 * 
	 * @param order 記入された注文情報
	 * @return 追加したときに採番されたid情報を加えたOrderItem
	 */
	public Order save(Order order){
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		// カートにひとつも商品が追加されていなければ新しくorderを追加する
		// カートが存在するならそれを更新する
		if(order.getId() == 0){			
			Number key = insert.executeAndReturnKey(param);
			order.setId(key.intValue());
		}else{
			template.update("update orders set user_id=:userId, status=:status, total_price=:totalPrice, order_date=:orderDate, "
					+ "destination_name=:destinationName, destination_email=:destinationEmail, destination_zipcode=:destinationZipcode, "
					+ "destination_address=:destinationAddress, destination_tel=:destinationTel, delivery_time=:deliveryTime, payment_method=:paymentMethod where id = :id", 
					param);
		}
		return order;
	}
	
	/**
	 * 注文情報をuserIdで取得.
	 * 
	 * @param userId 検索条件のuserId
	 * @return 取得したOrder情報、照合しなければnull
	 */
	public Order findByUserId(long userId){
		String sql = "select id, user_id, status, total_price, order_date, destination_name,"
				+ "destination_email, destination_zipcode, destination_address, destination_tel,"
				+ "delivery_time, payment_method "
				+ "from orders where user_id = :userId order by id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId",userId);
		Order order = new Order();
		try{			
			order = template.queryForObject(sql, param, orderRowMapper);
		}catch(DataAccessException e){
			order = null;
		}
		return order;
	}
	
	/**
	 * 注文データをユーザーIDと状態で検索.
	 * @param userId ユーザーID
	 * @param status　取引状態
	 * @return　取得した注文データ、取得できなければnull
	 */
	public Order finfByUserIdAndStatus(long userId, int status){
		String sql = "select id, user_id, status, total_price, order_date, destination_name,"
				+ "destination_email, destination_zipcode, destination_address, destination_tel,"
				+ "delivery_time, payment_method "
				+ "from orders where user_id = :userId and status = :status order by id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId",userId).addValue("status", status);
		Order order = new Order();
		try{
			order = template.queryForObject(sql, param, orderRowMapper);
		}catch(DataAccessException e){
			order = null;
		}
		return order;
	}
	
	/**
	 * 注文データを削除する.
	 * by shun.nakano
	 * 
	 * @param id 削除したい注文のID
	 */
	public void deleteByOrderId(long id){
		template.update("delete from orders where id = :id", 
				new MapSqlParameterSource().addValue("id", id));
	}

	
	/**
	 * 注文履歴をユーザーIDで検索.
	 * by ryo.kamiyama
	 * @param userId ユーザーID
	 * @return　取得した注文履歴のデータ、取得できなければnull
	 */
	
	public List<Order> finfByUserId(long userId){
		String sql = "select id, user_id, status,total_price, order_date, destination_name,"
				+ "destination_email, destination_zipcode, destination_address, destination_tel,"
				+ "delivery_time, payment_method "
				+ "from orders where user_id =:userId and ( status = 1 or status = 2 ) order by id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId",userId);
		List<Order> order = null;
		try{
			order = template.query(sql,param, orderRowMapper);			
		}catch(DataAccessException e){
			return null;			
		}
			return order ;
		
	}	

}