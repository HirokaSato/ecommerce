package jp.co.rakus.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.repository.ItemRepository;

@Controller
@RequestMapping
public class ProductManagementController {

	@Autowired
	private ItemRepository repository;
	
	@RequestMapping("/product_list")
	public String manage(Model model){
		List<Item> pizzaList=repository.findAllItem();
		model.addAttribute("pizzaList", pizzaList);
		return "allProduct";
	}
}
