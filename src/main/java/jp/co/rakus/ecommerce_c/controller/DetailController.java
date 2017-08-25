package jp.co.rakus.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.service.DetailService;

/**
 * 商品の詳細画面を操作するクラス.
 * 
 * @author ryo.kamiyama
 *
 */

@Controller
@RequestMapping("/detail")
public class DetailController {

	@Autowired
	private DetailService detailService;

	/**
	 * 商品の詳細を表示する
	 * 
	 * @return　商品詳細の画面
	 */	
	@RequestMapping
	public String detailOfItem(Integer id, Model model) {

		Item item = detailService.load(id);
		model.addAttribute("item", item);
		
		return "detail";
	}
	
}
