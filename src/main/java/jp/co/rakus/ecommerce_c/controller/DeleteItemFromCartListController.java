package jp.co.rakus.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private ViewCartListController controller; 
	
	@Autowired
	private DeleteItemFromCartListService service;

	@RequestMapping("/delete")
	public String delete(Integer userId,Model model,Integer itemId){
		userId = 1;//仮で
		service.execute(itemId);// orderItemId,itemId
		return controller.execute(userId,model);
	}
}
