package jp.co.rakus.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class OrderHistoryController {
	
	@RequestMapping("/orderHistory")
	public String orderHistory(){
		
		return "orderHistory" ;
		
	}

}
