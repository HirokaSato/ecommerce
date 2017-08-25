package jp.co.rakus.ecommerce_c.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommerce_c.domain.Topping;

/**
 * toppingsテーブルを操作するクラス.
 * 
 * @author ryo.kamiyama
 *
 */
@Repository
public class ToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	public final static RowMapper<Topping> toppingRowMapper = (rs, i) -> {
		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));
		return topping;
	};

	
	/**
	 * トッピング情報を全件表示する
	 * 
	 * @return toppingList toppingsテーブル全てのデータ
	 */
	public List<Topping> findAllTopping(){
		
		String findAllToppingSql ="select id,name,price_m,price_l from toppings";
		List<Topping> toppingList = template.query(findAllToppingSql,toppingRowMapper);
		return toppingList ;
		
	}
}
