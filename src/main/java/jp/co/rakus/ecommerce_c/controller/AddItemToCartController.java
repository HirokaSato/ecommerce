package jp.co.rakus.ecommerce_c.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.LoginUser;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
import jp.co.rakus.ecommerce_c.form.AddToCartForm;
import jp.co.rakus.ecommerce_c.service.AddItemToCartService;

/**
 * 商品をカートに入れる処理を行うコントローラ.
 * 
 * @author shun.nakano
 *
 */
@Controller
@RequestMapping("/addToCart")
public class AddItemToCartController {
	@Autowired
	private AddItemToCartService addItemToService;
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public AddToCartForm setUpForm(){
		return new AddToCartForm();
	}
	
	/**
	 * 商品をカートに入れる処理を行う.
	 * 
	 * @param form 入力情報
	 * @param loginUser ログイン状態
	 * @return ショッピングカート表示画面
	 */
	@RequestMapping
	public String addToCart(AddToCartForm form, @AuthenticationPrincipal LoginUser loginUser){
		Order order = null;
		// ユーザがログインしていればOrderテーブルにそのユーザIDを挿入する
		// ゲスト状態のときはセッションIDを仮に入れる
		if(loginUser == null){
			order = addItemToService.findByUserIdAndStatus((int)session.getAttribute("randomSessionId"), 0);
			// ゲスト状態のユーザーが未注文状態(カート内商品)を持たない場合は新しく未注文Orderをつくる
			if(order == null){
				order = new Order();
				order.setUserId((int)session.getAttribute("randomSessionId"));
				order.setStatus(0);
				order.setTotalPrice(0);
			}
		}else{
			order = addItemToService.findByUserIdAndStatus(loginUser.getUser().getId(), 0);
			// ログイン状態のユーザーが未注文状態(カート内商品)を持たない場合は新しく未注文Orderをつくる
			if(order == null){
				order = new Order();
				order.setUserId(loginUser.getUser().getId());
				order.setStatus(0);
				order.setTotalPrice(0);
			}
		}
		order = addItemToService.saveOrder(order);
		
		// フォームから注文情報を受け取る
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(order.getId());
		orderItem.setItemId(form.getIntItemId());
		orderItem.setSize(form.getSize());
		orderItem.setQuantity(form.getIntQuantity());
		orderItem = addItemToService.saveOrderItem(orderItem);
		
		// 注文されたトッピング情報からトッピングのListをつくる
		List<OrderTopping> orderToppingList = new ArrayList<>();
		for(Integer orderToppingId : form.getToppingList()){
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setToppingId(orderToppingId);
			orderTopping.setOrderItemId(orderItem.getId());
			orderToppingList.add(orderTopping);
		}
		addItemToService.saveOrderToppings(orderToppingList);
		
		Integer totalPrice = addItemToService.getTotalPrice(order.getId(), orderItem.getId(), orderToppingList);
		order.setTotalPrice(totalPrice);
		addItemToService.saveOrder(order);
		
		return "redirect:/viewCartList";
	}
}
