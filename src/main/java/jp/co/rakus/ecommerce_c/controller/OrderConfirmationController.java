package jp.co.rakus.ecommerce_c.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
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
	private OrderConfirmationService orderConfirmationService;
	
	@Autowired
	private HttpSession session;
	

	
	/**
	 * 注文確認画面を表示する.
	 * @param userId ログインユーザーのId
	 * @param model　モデル
	 * @return　注文確認画面
	 */
	@RequestMapping("/")
	public String index(Model model){
		session.setAttribute("userId", 00000000000000000000);
		Order order= orderConfirmationService.findByUserIdAndStatus((Integer)session.getAttribute("userId"));		
		List<OrderItem> orderItemList = orderConfirmationService.findByOrderId(order.getId());
		List<OrderItem> doOrderItemList = new ArrayList<>();//注文する商品リスト
		for(OrderItem orderItem : orderItemList){
			Item item = orderConfirmationService.findByItemId(orderItem.getItemId());
			orderItem.setItem(item);//商品の詳細格納
			List<OrderTopping> orderToppingList = orderConfirmationService.toppingFindByOrderItemId(orderItem.getId());
			for(OrderTopping orderTopping :orderToppingList){
				
				orderTopping.setTopping(orderConfirmationService.toppingFindByToppingId(orderTopping.getToppingId()));
			
				orderToppingList.add(orderTopping);
			}
			
			doOrderItemList.add(orderItem);
			}
		
		model.addAttribute("orderItemList",orderItemList);
		return "orderList";
	}

}
