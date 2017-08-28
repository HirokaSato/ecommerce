package jp.co.rakus.ecommerce_c.controller;

import java.util.ArrayList;
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
import jp.co.rakus.ecommerce_c.domain.Topping;
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
	
	

	
	/**
	 * 注文確認画面を表示する.
	 * @param userId ログインユーザーのId
	 * @param model　モデル
	 * @return　注文確認画面
	 */
	@RequestMapping("/")
	public String index(@AuthenticationPrincipal LoginUser loginUser,Model model){
		Order order= orderConfirmationService.findByUserIdAndStatus(0);	
		System.out.println("注文のIDは"+order.getId());
		List<OrderItem> orderItemList = orderConfirmationService.findByOrderId(order.getId());
		List<OrderItem> doOrderItemList = new ArrayList<>();//注文する商品リスト
		for(OrderItem orderItem : orderItemList){
			Item item = orderConfirmationService.findByItemId(orderItem.getItemId());
			orderItem.setItem(item);//商品の詳細格納
			List<OrderTopping> orderToppingList = orderConfirmationService.toppingFindByOrderItemId(orderItem.getId());
			System.out.println("注文の商品Id"+orderItem.getId()+"データの数"+orderToppingList.size());
			List<OrderTopping> doOrderToppingList = new ArrayList<>();
			for(OrderTopping orderTopping :orderToppingList){
					
					int toppingId = orderTopping.getToppingId();
					Topping topping = orderConfirmationService.toppingFindByToppingId(toppingId);
					orderTopping.setTopping(topping);
					doOrderToppingList.add(orderTopping);
			}
			orderItem.setOrderToppingList(doOrderToppingList);
			}
		
		model.addAttribute("orderItemList",orderItemList);
		order.setOrderItemList(doOrderItemList);
		model.addAttribute("order",order);
		model.addAttribute("tax", order.getTax());
		model.addAttribute("taxIncludedAmount", order.getCalcTotalPrice());
		
		return "orderList";
	}

}
