package jp.co.rakus.ecommerce_c.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * ログインしているユーザーを表すドメイン<br>
 * SpringSecurity用のクラス.
 * 
 * @author shun.nakano
 *
 */
public class LoginUser extends org.springframework.security.core.userdetails.User{
	private static final long serialVersionUID = 1L;
	
	private User user;

	public LoginUser(User user, Collection<GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		this.setUser(user);
		
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
