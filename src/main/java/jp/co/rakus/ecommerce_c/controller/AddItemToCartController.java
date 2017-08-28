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
	
	@RequestMapping
	public String addToCart(AddToCartForm form, @AuthenticationPrincipal LoginUser loginUser){
		Order order = new Order();
		if(loginUser.getUser() == null){
			order.setUserId(session.getCreationTime());
		}else{
			order.setUserId(loginUser.getUser().getId());
		}
		order.setStatus(0);
		
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(form.getIntItemId());
		orderItem.setSize(form.getSize());
		orderItem.setQuantity(form.getIntQuantity());
		
		Integer totalPrice = addItemToService.getTotalPrice(orderItem);
		order.setTotalPrice(totalPrice);
		
		addItemToService.execute(order, orderItem);
		return "redirect:/viewCartList";
	}
}
