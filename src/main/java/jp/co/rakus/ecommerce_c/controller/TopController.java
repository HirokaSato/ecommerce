package jp.co.rakus.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.service.TopService;

/**
 * top画面を操作するクラス.
 * 
 * @author ryo.kamiyama
 *
 */
@Controller
@RequestMapping
public class TopController {

	@Autowired
	private TopService itemService;

	/**
	 * 初期画面を表示する
	 * 
	 * @return　Top画面
	 */
	@RequestMapping("/top")
	public String top(Model model) {

		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		return "itemList";
	}

}