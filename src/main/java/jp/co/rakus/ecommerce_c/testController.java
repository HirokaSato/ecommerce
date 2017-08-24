package jp.co.rakus.ecommerce_c;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("test")
public class testController {
	@RequestMapping("/")
	public String test(){
		
		return "order_list";
	}
	

}
