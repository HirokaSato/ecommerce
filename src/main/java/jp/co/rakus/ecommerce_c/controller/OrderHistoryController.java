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

@Controller
@RequestMapping
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService;

	@RequestMapping("/orderHistory")
	public String orderHistory(@AuthenticationPrincipal LoginUser loginUser, Model model) {

		long userId = loginUser.getUser().getId();
		List<Order> orderHistory = orderHistoryService.finfByUserId(userId);
		model.addAttribute("orderHistory", orderHistory);
		
		if(orderHistory == null){
			model.addAttribute("nothing","注文履歴がありません");			
		}
		
		return "orderHistory";

	}
}
