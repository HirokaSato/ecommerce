package jp.co.rakus.ecommerce_c.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.LoginUser;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
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
		if(form.getItemId() == null){
			return "redirect:/top";
		}
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
				order = addItemToService.saveOrder(order);
			}
		}else{
			order = addItemToService.findByUserIdAndStatus(loginUser.getUser().getId(), 0);
			// ログイン状態のユーザーが未注文状態(カート内商品)を持たない場合は新しく未注文Orderをつくる
			if(order == null){
				order = new Order();
				order.setUserId(loginUser.getUser().getId());
				order.setStatus(0);
				order.setTotalPrice(0);
				order = addItemToService.saveOrder(order);
			}
		}
		
		if(!addItemToService.checkDuplicationOrderItem(order, form)){
			// 注文された商品情報をDBに登録する
			OrderItem orderItem = addItemToService.saveOrderItem(order, form);
			// 注文されたトッピング情報をDBに登録する
			addItemToService.saveOrderToppings(form, orderItem);
		}
		
		Integer totalPrice = addItemToService.getTotalPrice(order.getId(), form);			
		order.setTotalPrice(totalPrice);
		addItemToService.saveOrder(order);
		
		return "redirect:/viewCartList";
	}
}
