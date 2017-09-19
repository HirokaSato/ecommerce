package jp.co.rakus.ecommerce_c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.service.SearchItemService;
import jp.co.rakus.ecommerce_c.service.TopService;

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
	
	@Autowired
	private TopService topService;

	/**
	 * 曖昧検索の結果画面を表示する
	 * 
	 * @param keyword
	 *            検索ワード
	 * @param model
	 *            モデル
	 * @return Top画面
	 */
	@ResponseBody
	@RequestMapping("/search_item")
	public List<Item> searchItem(String search_word) {

		return searchItemService.searchItem(search_word);
	}
		/*if (searchItem.isEmpty()) {
			model.addAttribute("error", "該当する商品がありません");
			keyword = "";
			List<Item> allItem = searchItemService.searchItem(keyword);
			model.addAttribute("itemList", allItem);
		}*/

	@ResponseBody
	@RequestMapping("/ajaxSerchResult")
	public List<Item> searchItemByAjax(String search_word) {

		return topService.findAllItem();

	}

}
