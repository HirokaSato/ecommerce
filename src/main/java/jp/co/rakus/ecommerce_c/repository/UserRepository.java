package jp.co.rakus.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import jp.co.rakus.ecommerce_c.domain.User;

/**
 * usersテーブルを操作するリポジトリ.
 * 
 * @author shun.nakano
 *
 */
public class UserRepository {
	@Autowired
	NamedParameterJdbcTemplate template;
	
	private final static RowMapper<User> userRowMapper = (rs,i)->{
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};
	
	/**
	 * メールアドレスからユーザ情報を検索する.
	 * 
	 * @param email 検索条件のメールアドレス
	 * @return User 照合したユーザ情報
	 */
	public User findByMailAddress(String email){
		return template.queryForObject(
				"select id, name, email, password, address, telephone from users where email = :email", 
				new MapSqlParameterSource().addValue("email", email), 
				userRowMapper);
	}
}
