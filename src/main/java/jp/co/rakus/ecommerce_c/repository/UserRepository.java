package jp.co.rakus.ecommerce_c.repository;

import java.sql.Timestamp;
import java.util.List;

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

	private final static RowMapper<User> userRowMapper = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		user.setManage(rs.getBoolean("manage"));
		user.setLastLogin(rs.getTimestamp("last_login"));
		return user;
	};
	
	/**
	 * ユーザー全件検索
	 * @return　すべてのユーザー情報
	 */
	public List<User> findAll(){
		return template.query("select id,name,email,password,address,telephone,manage,last_login from users", userRowMapper);
	}

	/**
	 * メールアドレスからユーザ情報を検索する.
	 * 
	 * @param email
	 *            検索条件のメールアドレス
	 * @return User 照合したユーザ情報 なければ null
	 */
	public User findByMailAddress(String email) {
		try {
			return template.queryForObject(
					"select id, name, email, password, address, telephone,manage,last_login from users where email = :email",
					new MapSqlParameterSource().addValue("email", email), userRowMapper);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * ユーザ情報を登録する.
	 * 
	 * @param user
	 *            登録するユーザ情報
	 * @return ユーザ情報
	 */
	public User insert(User user) {
		template.update(
				"insert into users (name, email, password, address, telephone) values (:name, :email, :password, :address, :telephone)",
				new BeanPropertySqlParameterSource(user));
		return user;
	}

	/**
	 * ユーザー情報の変更をする
	 * 
	 * @param user
	 *            変更したユーザ情報
	 * @return ユーザー情報
	 */
	public User update(User user) {
		template.update(
				"update users set name=:name,email=:email,password=:password,address=:address,telephone=:telephone where id=:id",
				new BeanPropertySqlParameterSource(user));
		return user;
	}

	/**
	 * ログイン時間を更新する
	 * 
	 * @param user
	 *            ログインユーザー
	 */
	public void insertLastLogin(User user) {
		template.update("update users set last_login=now() where email=:email",
				new BeanPropertySqlParameterSource(user));
	}

}
