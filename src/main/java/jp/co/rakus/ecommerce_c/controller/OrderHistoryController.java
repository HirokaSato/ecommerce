package jp.co.rakus.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class OrderHistoryController {
	
	@RequestMapping("/orderhistory")
	public String orderHistory(){
		
		return "orderHistory" ;
		
	}

}
