package jp.co.rakus.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.service.SearchItemService;

/**
 * 曖昧検索を操作するクラス.
 * 
 * @author ryo.kamiyama
 *
 */

@Controller
@RequestMapping
public class SearchItemController {
	
	@Autowired
	private SearchItemService searchItemService;

	/**
	 * 曖昧検索の結果画面を表示する
	 * 
	 * @return　Top画面
	 */
	
	@RequestMapping("/searchItem")
	public String searchItem(String keyword,Model model){
						
		List<Item> searchItem = searchItemService.searchItem(keyword);
		model.addAttribute("itemList",searchItem);		
		
		if(searchItem.isEmpty()){
			model.addAttribute("error","該当する商品がありません");	
			keyword = "";
			List<Item> searchItem2 = searchItemService.searchItem(keyword);
			model.addAttribute("itemList",searchItem2);
		}
		
		return "itemList" ;
		
	}
}
