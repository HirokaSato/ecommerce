package jp.co.rakus.ecommerce_c.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import jp.co.rakus.ecommerce_c.domain.Item;


/**
 * itemsテーブルを操作するクラス.
 * 
 * @author ryo.kamiyama
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<Item> itemRowMapper = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;

	};

	/**
	 * 商品情報を全件表示する.
	 * 
	 * @return itemList 
	 */
	public List<Item> findAll(){
		
		String findAllSql ="select id,name,description,price_m,price_l,image_path,deleted from items";
	    List<Item> itemList =template.query(findAllSql,itemRowMapper);		
		return itemList; 

	}
	
	
	public Item load(Integer id){
		
		
		return null;
	}
	
}
