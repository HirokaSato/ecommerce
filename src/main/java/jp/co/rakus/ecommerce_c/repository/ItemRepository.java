package jp.co.rakus.ecommerce_c.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
		item.setPopularity(rs.getInt("popularity"));
		return item;

	};

	/**
	 * 人気トップ10のピザ商品を取り出す
	 * 
	 * @return 全商品の名前
	 */
	public List<Item> findTop10() {
		return template.query(
				"SELECT id,name,description,price_m,price_l,image_path,deleted,popularity FROM items ORDER BY popularity desc OFFSET 0 LIMIT 10",
				itemRowMapper);
	}

	/**
	 * 商品情報を全件表示する.
	 * 
	 * @return itemList itemsテーブル全てのデータ
	 */
	public List<Item> findAllItem() {

		String findAllItemSql = "select id,name,description,price_m,price_l,image_path,deleted,popularity from items order by price_m";
		List<Item> itemList = template.query(findAllItemSql, itemRowMapper);
		return itemList;

	}

	/**
	 * 商品IDを元に商品情報を検索する.
	 * 
	 * @param id
	 *            商品ID
	 * 
	 * @return item 検索した商品データ
	 */

	public Item loadItem(long id) {

		String loadItemSql = "select id,name,description,price_m,price_l,image_path,deleted,popularity from items where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item item = null;
		try {
			item = template.queryForObject(loadItemSql, param, itemRowMapper);
		} catch (DataAccessException e) {
			return null;
		}
		return item;

	}

	/**
	 * 入力情報を元に曖昧検索をする.
	 * 
	 * @param keyword
	 *            入力ワード
	 * @return searchItemList 検索した商品データ
	 */

	public List<Item> searchItem(String keyword) {

		String searchSql = "select id,name,description,price_m,price_l,image_path,deleted,popularity from items where name like :keyword ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("keyword", "%" + keyword + "%");
		List<Item> searchItemList = template.query(searchSql, param, itemRowMapper);
		return searchItemList;

	}

	/**
	 * 商品の追加、更新
	 * 
	 * @param item
	 *            追加情報
	 * @return 結果
	 */
	public Item save(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		if (item.getStringId() == null) {
			String insertSql = "insert into items (name,description,price_m,price_l,image_path,deleted,popularity)"
					+ "values(name,description,price_m,price_l,image_path,deleted,popularity)";
			template.update(insertSql, param);
		} else {
			String updateSql = "update items set name=:name,description=:description,price_m=:price_m,price_l=:price_l,image_path=:image_path,deleted=:deleted,popularity=:popularity where id=:id";
			template.update(updateSql, param);
		}
		return item;
	}
	
	/**
	 * テスト用登録User全件削除.
	 */
	public void deleteAll() {
		String sql = "delete from items";
		SqlParameterSource param = new MapSqlParameterSource();
		template.update(sql, param);
	}


}
