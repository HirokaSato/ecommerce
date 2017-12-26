package jp.co.rakus.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.User;
import jp.co.rakus.ecommerce_c.repository.UserRepository;

/**
 * ユーザ登録を実行するサービス.
 * 
 * @author shun.nakano
 *
 */
@Service
public class RegisterUserService {
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * ユーザ登録を実行する.
	 * 
	 * @param user 登録したいユーザ情報
	 */
	public void registUser(User user){
		userRepository.insert(user);
	}
	
	/**
	 * 入力されたメールアドレスからユーザが登録済みかを調べる.
	 * 
	 * @param email 入力されたメールアドレス
	 * @return 存在しなければnull
	 */
	public User findByEmail(String email){
		return userRepository.findByMailAddress(email);
	}
	
	/**
	 * ユーザー情報を更新する.
	 * 
	 * @param user 変更したユーザー情報
	 */
	public void updateUser(User user){
		userRepository.update(user);
	}
	
}
