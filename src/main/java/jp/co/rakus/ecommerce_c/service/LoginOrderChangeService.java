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
	 * <pre>
	 * ゲスト状態のカート(Order)の中身があればユーザ情報を書き換える
	 * 以前のログイン時に未注文の商品が残っていれば、そのOrder情報に現在のカート内容を追加する.
	 * </pre
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
