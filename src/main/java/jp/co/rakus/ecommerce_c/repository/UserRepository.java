package jp.co.rakus.ecommerce_c.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ecommerce_c.domain.User;

/**
 * usersテーブルを操作するリポジトリ.
 * 
 * @author shun.nakano
 *
 */
@Repository
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
		System.out.println(i);//iは、rsのカウント数
		return user;
	};
	
	/**
	 * メールアドレスからユーザ情報を検索する.
	 * 
	 * @param email 検索条件のメールアドレス
	 * @return User 照合したユーザ情報 なければ null
	 */
	public User findByMailAddress(String email){
		try{
			return template.queryForObject(
					"select id, name, email, password, address, telephone from users where email = :email", 
					new MapSqlParameterSource().addValue("email", email), 
					userRowMapper);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * ユーザ情報を登録する.
	 * 
	 * @param user 登録するユーザ情報
	 * @return ユーザ情報
	 */
	public User insert(User user){
		template.update("insert into users (name, email, password, address, telephone) values (:name, :email, :password, :address, :telephone)", 
				new BeanPropertySqlParameterSource(user));
		return user;
	}
}
