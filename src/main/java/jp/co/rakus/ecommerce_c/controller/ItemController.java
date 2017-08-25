package jp.co.rakus.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.service.ItemService;

@Controller
@RequestMapping
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/top")
	public String top(Model model) {

		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		return "itemList";
	}

}
