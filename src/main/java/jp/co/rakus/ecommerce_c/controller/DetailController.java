package jp.co.rakus.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.domain.Topping;
import jp.co.rakus.ecommerce_c.form.addToCartForm;
import jp.co.rakus.ecommerce_c.service.DetailService;

/**
 * 商品の詳細画面を操作するクラス.
 * 
 * @author ryo.kamiyama
 *
 */

@Controller
@RequestMapping("/detailController")
public class DetailController {

	@Autowired
	private DetailService detailService;
	
	/**
	 * 商品の詳細を表示する
	 * 
	 * @return　商品詳細の画面
	 */	

	@RequestMapping("/detail")
	public String detailOfItem(Integer id, Model model) {

		Item item = detailService.load(id);
		model.addAttribute("item", item);
		
		List<Topping> toppingList = detailService.findAllTopping();
		model.addAttribute("toppingList",toppingList);
		
		return "itemDetail";
	}
	
	
	
	
	
}	