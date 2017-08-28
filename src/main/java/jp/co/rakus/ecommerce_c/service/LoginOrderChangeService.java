package jp.co.rakus.ecommerce_c.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.User;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;

/**
 * ログイン時にカート(Order)の中身があればユーザ情報を書き換えるためのサービスクラス.
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
	 * ログイン時にカート(Order)の中身があればユーザ情報を書き換える.
	 * 
	 * @param user
	 */
	public void execute(User user){
		Order order = orderRepository.finfByUserIdAndStatus((int)session.getAttribute("randomSessionId"), 0);
		if(order != null){			
			order.setUserId(user.getId());
			orderRepository.save(order);
		}
	}
}
