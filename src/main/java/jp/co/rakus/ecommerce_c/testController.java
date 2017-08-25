package jp.co.rakus.ecommerce_c;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class testController {
	@RequestMapping("/")
	public String test(){
		
		return "orderList";
	}
	
	@RequestMapping("/viewItemDetailTest")
	public String viewItemDetailTest(){
		
		return "itemDetail";
	}
	
	@RequestMapping("/viewOrderFinish")
	public String viewOrderFinish(){
		
		return "orderFinished";
	}
	

}
