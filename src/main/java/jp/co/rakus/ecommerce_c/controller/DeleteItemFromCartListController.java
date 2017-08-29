package jp.co.rakus.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.service.DeleteItemFromCartListService;

/**
 * カート内の注文商品を削除するコントローラークラス.
 * 
 * @author rui.toguchi
 *
 */
@Controller
@RequestMapping("/delteCartItem")
public class DeleteItemFromCartListController {
	
	@Autowired
	private DeleteItemFromCartListService service;

	@RequestMapping("/delete")
	public String delete(Integer userId,Integer id,Integer itemId,Integer subTotalPrice){
		service.execute(userId,id,itemId,subTotalPrice);
		return "redirect:/viewCartList";
	}
}
