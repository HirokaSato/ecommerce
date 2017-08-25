package jp.co.rakus.ecommerce_c.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommerce_c.domain.Order;

/**
 * 注文リポジトリ
 * @author rakus
 *
 */
@Repository
public class OrderRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
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
	public List<Order> findbyId(Long id,Integer status){
		String sql = "select id, user_id, status, total_price, order_date, destination_name,"
				+ "destination_email, destination_zipcode, destination_address, destination_tel,"
				+ "delivery_time, payment_method "
				+ "from orders where id = :id status = :status order by id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id).addValue("status", status);
		List<Order> orderList = template.query(sql, param, orderRowMapper);
		return orderList;
		
	}
	
	/**
	 * 注文情報をDBに保存.
	 * @param order 記入された注文情報
	 */
	public void save(Order order){
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "insert into orders(id, user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,"
				+ "destination_tel,delivery_time,payment_method)"
				+ "values(:id, :userId, :status, :totalPrice, :orderDate, :destinationName, :destinationEmail, :destinationZipcode,:destinationAddress,destinationTel,:deliveryTime,:paymentMethod);";
		template.update(sql, param);
	}
	
	/**
	 * 注文情報をuserIdで取得.
	 * 
	 * @param userId
	 * @return
	 */
	public Order findByUserId(long userId){
		String sql = "select id, user_id, status, total_price, order_date, destination_name,"
				+ "destination_email, destination_zipcode, destination_address, destination_tel,"
				+ "delivery_time, payment_method "
				+ "from orders where user_id = :userId;";
		System.out.println("sql :  " + sql);
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId",userId);
		Order order = template.queryForObject(sql, param, orderRowMapper);
		return order;
	}
	
	
}
