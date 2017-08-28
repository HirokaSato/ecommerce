package jp.co.rakus.ecommerce_c.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.domain.Topping;
import jp.co.rakus.ecommerce_c.form.AddToCartForm;
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
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public AddToCartForm setUpForm(){
		return new AddToCartForm();
	}
	
	/**
	 * 商品の詳細を表示する
	 * 
	 * @return　商品詳細の画面
	 */	

	@RequestMapping("/detail")
	public String detailOfItem(Integer id, Model model) {
		if(session.getAttribute("randomSessionId") == null){
			session.setAttribute("randomSessionId", new Random().nextInt(1147483640)+1000000000);
		}

		//idを元に商品詳細を表示
		Item item = detailService.load(id);
		model.addAttribute("item", item);
				
		//toppingリストの詳細を表示
		List<Topping> toppingList = detailService.findAllTopping();
		Map<Integer, String> toppingMap = new LinkedHashMap<Integer, String>();
		
		for(Topping topping : toppingList){
			toppingMap.put(topping.getId(), topping.getName());
		}
		model.addAttribute("toppingMap", toppingMap);
		return "itemDetail";
	
	}	
}	