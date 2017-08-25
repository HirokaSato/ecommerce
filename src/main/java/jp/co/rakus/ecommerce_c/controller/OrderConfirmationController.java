package jp.co.rakus.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.service.OrderConfirmationService;

/**
 * 注文確認画面を表示するコントローラー.
 * @author atsuko.yoshino
 *
 */
@Controller
@RequestMapping("OrderConfirmationController")
public class OrderConfirmationController {
	@Autowired
	private OrderConfirmationService confirmationService;

	
	/**
	 * 注文確認画面を表示する.
	 * @param userId ログインユーザーのId
	 * @param model　モデル
	 * @return　注文確認画面
	 */
	@RequestMapping("/")
	public String index(Long userId, Model model){
		
		 List<Order> orderList= confirmationService.findById(userId, 0);
		 List<OrderItem> orderItemList = null;
		 for(Order order:orderList){
			 
			 orderItemList = confirmationService.findByOrderId(order.getId());
			
		 }
		 
		 model.addAttribute("orderItemList", orderItemList);
		 return "orderList";
		 
		 
	}

}
