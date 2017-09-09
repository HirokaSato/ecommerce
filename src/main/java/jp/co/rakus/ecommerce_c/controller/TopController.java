package jp.co.rakus.ecommerce_c.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@Autowired
	private HttpSession session;

	/**
	 * 初期画面を表示する
	 * 
	 * @param model
	 *            モデル
	 * @return Top画面
	 */
	@RequestMapping("/top")
	public String top(Model model) {
		// セッションIDがなければセット
		if (session.getAttribute("randomSessionId") == null) {
			session.setAttribute("randomSessionId", new Random().nextInt(1147483640) + 1000000000);
		}
//		List<Item> itemList=itemService.findAllItem();
//		model.addAttribute("itemList", itemList);
		return "itemList";
	}

	// returnでjspではなく文字列を返すアノテーション
	@ResponseBody
	@RequestMapping("/ajaxSearchAllItem")
	public List<Item> searchItemByAjax(String searchWord) {
		return itemService.findAllItem();
		//return "[ {\"name\": \"pizza\" } ]";
	}

}
