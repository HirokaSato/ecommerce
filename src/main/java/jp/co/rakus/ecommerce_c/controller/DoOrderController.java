package jp.co.rakus.ecommerce_c.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import jp.co.rakus.ecommerce_c.domain.Order;

import jp.co.rakus.ecommerce_c.form.AddToCartForm;

import jp.co.rakus.ecommerce_c.form.OrderForm;
import jp.co.rakus.ecommerce_c.service.doOrderService;

/**
 * 注文を確定するクラス（注文情報を受け取るクラス).
 * 
 * @author rakus
 *
 */
@Controller
@RequestMapping("/doOrderController")
public class DoOrderController {
	@Autowired
	private doOrderService service;
	
	
	@ModelAttribute
	private OrderForm setUp(){	
		return new OrderForm();
	}
	

	@ModelAttribute
	private AddToCartForm setUpaddToCart(){
		return new AddToCartForm();
	}

	/**
	 * 注文情報を受け取ります.
	 * @return 完了したら、orderFinishへ
	 *			入力ミス等はorderListへ 
	 */

	@RequestMapping("/addToCart")
	public String addToCart(AddToCartForm form){
		
		return null;
		
	}


	/**
	 * 注文情報を受け取り、DBに格納する.
	 * @param form 注文情報
	 * @return　完了画面
	 * @throws ParseException　Timestamp解析例外
	 */
	@RequestMapping("/order")
	public String insert(OrderForm form) throws ParseException{
		
		Order order = new Order();
		order.setDestinationName(form.getName());
		order.setDestinationEmail(form.getEmail());
		order.setDestinationZipcode(form.getZipcode());
		order.setDeliveryTime(form.getTimeStampDeliveryDate());
		order.setPaymentMethod(form.getIntPaymentMethod());
		order.setUserId(form.getLongUserId());
		order.setId(form.getLongId());
		service.save(order);
		
		return "orderFinish";
	}
}
