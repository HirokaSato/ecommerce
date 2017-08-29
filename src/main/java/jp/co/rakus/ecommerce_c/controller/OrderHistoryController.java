package jp.co.rakus.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.domain.LoginUser;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
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
	 * @param userId ログインユーザーのId
	 * @param model　モデル
	 * @return　注文履歴画面
	 */
	
	@RequestMapping("/orderHistory")
	public String orderHistory(@AuthenticationPrincipal LoginUser loginUser, Model model) {

		// ログインしているユーザーIDを元に検索
		long userId = loginUser.getUser().getId();
		List<Order> orderHistory = orderHistoryService.finfByUserId(userId);
		model.addAttribute("orderHistory", orderHistory);

		// ユーザーIDをオーダーIDに代入して検索
		long id = userId;
		OrderItem orderItemHistory = orderHistoryService.findById(id);
		model.addAttribute("orderItemHistory", orderItemHistory);

		// オーダーIDをオーダーアイテムIDに代入して検索
		long orderItemId = id;
		List<OrderTopping> orderToppingHistory = orderHistoryService.findByOrderItemId(orderItemId);
		model.addAttribute("orderToppingHistory", orderToppingHistory);

		// オーダーアイテムIDをアイテムIDに代入して検索
		long itemId = orderItemHistory.getItemId();
		Item item = orderHistoryService.load(itemId);
		model.addAttribute("item", item);

		if (orderHistory == null) {
			model.addAttribute("nothing", "注文履歴がありません");
		}

		return "orderHistory";

	}
}
