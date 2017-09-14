
package jp.co.rakus.ecommerce_c.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.LoginUser;
import jp.co.rakus.ecommerce_c.domain.User;
import jp.co.rakus.ecommerce_c.repository.UserRepository;

/**
 * Security用のUserDetailsService実装クラス.
 * 
 * @author shun.nakano
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{
	//UserDetailsServiceはspringsecutiryが用意したクラス
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LoginOrderChangeService loginOrderChangeService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByMailAddress(email);
		if(user == null){
			throw new UsernameNotFoundException("そのメールアドレスは登録されていません");
		}
		//Collection⇒mapやListのおおもと。mapやlistはcollectionを継承してオブジェクトを生成
		Collection<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		//GrantedAuthority　⇒　認可情報を表すことができるクラス（springsecurityの中に入っている）
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));//「ROLE_」⇒user権限をuserに渡してる。
		//ログイン日時をDBに挿入
		userRepository.insertLastLogin(user);
		if(user.isManage()){
		authorityList.add(new SimpleGrantedAuthority("ROLE_MANAGE"));//「ROLE_」⇒manage権限をuserに渡してる。
		}
		
		// ログイン時にカートに商品が入っていれば情報を書き換える
		loginOrderChangeService.execute(user);
		return new LoginUser(user, authorityList);
	}

}
