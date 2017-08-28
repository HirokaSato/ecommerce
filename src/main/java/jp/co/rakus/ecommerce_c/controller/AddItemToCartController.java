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
		Order order = new Order();
		// ユーザがログインしていればOrderテーブルにそのユーザIDを挿入する
		// ゲスト状態のときはセッションIDを仮に入れる
		if(loginUser == null){
			order.setUserId((long)session.getAttribute("randomSessionId"));
		}else{
			order.setUserId(loginUser.getUser().getId());
		}
		
		order.setStatus(0);
		
		// フォームから注文情報を受け取る
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(form.getIntItemId());
		orderItem.setSize(form.getSize());
		orderItem.setQuantity(form.getIntQuantity());
		
		// 合計金額を取得、セット
		Integer totalPrice = addItemToService.getTotalPrice(orderItem);
		order.setTotalPrice(totalPrice);
		
		// 注文されたトッピング情報からトッピングのListをつくる
		List<OrderTopping> orderToppingList = new ArrayList<>();
		for(Integer orderToppingId : form.getToppingList()){
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setToppingId(orderToppingId);
			orderTopping.setOrderItemId(form.getIntItemId());
			orderToppingList.add(orderTopping);
		}
		orderItem.setOrderToppingList(orderToppingList);
		
		addItemToService.execute(order, orderItem);
		return "redirect:/viewCartList";
	}
}
