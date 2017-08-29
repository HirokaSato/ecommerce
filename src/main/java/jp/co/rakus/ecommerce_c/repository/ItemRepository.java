package jp.co.rakus.ecommerce_c.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	 * @return itemList itemsテーブル全てのデータ
	 */
	public List<Item> findAll() {

		String findAllSql = "select id,name,description,price_m,price_l,image_path,deleted from items";
		List<Item> itemList = template.query(findAllSql, itemRowMapper);
		return itemList;

	}

	/**
	 * 商品IDを元に商品情報を検索する.
	 * 
	 * @param id　商品ID
	 *            
	 * @return item 検索した商品データ
	 */

	public Item load(long id) {

		String loadSql = "select id,name,description,price_m,price_l,image_path,deleted from items where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = template.queryForObject(loadSql, param, itemRowMapper);
		return item;

	}

	/**
	 * 入力情報を元に曖昧検索をする.
	 * 
	 * @param keyword 入力ワード
	 *         
	 * @return searchItemList 検索した商品データ
	 */
	
	public List<Item> searchItem(String keyword) {
				
		String searchSql = "select id,name,description,price_m,price_l,image_path,deleted from items where name like :keyword ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("keyword", "%" + keyword + "%");
		List<Item> searchItemList = template.query(searchSql, param, itemRowMapper);
		return searchItemList;

	}

}
