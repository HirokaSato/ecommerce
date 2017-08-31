package jp.co.rakus.ecommerce_c.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.LoginUser;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.service.OrderHistoryService;

/**
 * 注文履歴を表示するクラス
 * 
 * @author ryo.kamiyama
 *
 */

@Controller
@RequestMapping
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService;

	/**
	 * 注文履歴を表示する.
	 * 
	 * @param userId ログインユーザーのId
	 * @param model モデル
	 * @return 注文履歴画面
	 */
	@RequestMapping("/orderHistory")
	public String orderHistory(@AuthenticationPrincipal LoginUser loginUser, Model model) {

		long userId = loginUser.getUser().getId();
		List<Order> orderList = orderHistoryService.execute(userId);
		model.addAttribute("orderList", orderList);
		
		return "orderHistory";

	}
}
