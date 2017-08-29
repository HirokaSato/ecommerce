package jp.co.rakus.ecommerce_c.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.User;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;

/**
 * ログイン時にカートの内容を操作するサービスクラス.
 * 
 * @author shun.nakano
 *
 */
@Service
public class LoginOrderChangeService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private HttpSession session;
	
	/**
	 * ゲスト状態のカート(Order)の中身があればユーザ情報を書き換える.
	 * 
	 * @param user
	 */
	public void execute(User user){
		Order order = new Order();
		// ゲスト状態のカートの中身があればユーザ情報を書き換える
		order = orderRepository.finfByUserIdAndStatus((int)session.getAttribute("randomSessionId"), 0);
		if(order != null){
			// ログインユーザが未注文状態のOrderをもたなければ情報の上書き、もっていれば追加する
//			if(orderRepository.finfByUserIdAndStatus(user.getId(), 0) == null){				
				order.setUserId(user.getId());
				orderRepository.save(order);
//			}else{
//				addCartFromGuestCart(order, user);
//			}
		}
	}
	
	/**
	 * ユーザが持つ未注文状態の注文に、ゲスト時のカート内容を追加する.
	 */
	public static void addCartFromGuestCart(Order order, User user){
		
	}
}
