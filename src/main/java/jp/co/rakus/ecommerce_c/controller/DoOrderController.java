package jp.co.rakus.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
	/**
	 * 注文情報を受け取ります.
	 * @return 完了したら、orderFinishへ
	 *			入力ミス等はorderListへ 
	 */
	@RequestMapping("/order")
	public String insert(){
		
		return "null";
	}
	
}
