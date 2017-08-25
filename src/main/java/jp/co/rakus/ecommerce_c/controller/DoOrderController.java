package jp.co.rakus.ecommerce_c.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.service.doOrderService;

/**
 * 注文情報を受け取るだけのクラスです。
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
	
	/**
	 * 注文情報を受け取ります.
	 * @return 完了したら、orderFinishへ
	 *			入力ミス等はorderListへ 
	 */
	
	//未完成
//	@RequestMapping("/order")
//	public String insert(OrderForm form) throws ParseException{
//		
//		Order order = new Order();
//		order.setDestinationName(form.getName());
//		order.setDestinationEmail(form.getEmail());
//		order.setDestinationZipcode(form.getZipcode());
//		order.setDeliveryTime(form.getTimeStampDeliveryDate());
//		order.setPaymentMethod(form.getIntPaymentMethod());
//		
//		return "null";
//		
		
		
	
	
}
