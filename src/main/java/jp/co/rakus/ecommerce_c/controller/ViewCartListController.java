package jp.co.rakus.ecommerce_c.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.LoginUser;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.service.ViewCartListService;

/**
 * カート内の商品を表示するコントローラークラス.
 * 
 * @author rui.toguchi
 *
 */
@Controller
@RequestMapping("/viewCartList")
public class ViewCartListController {

	@Autowired
	private ViewCartListService viewCartListservice;
	
	@Autowired
	private HttpSession session;

	/**
	 * 実行メソッド.
	 * 
	 * @param model リクエストパラメータ
	 * @param loginUser ログイン情報
	 * @return ショッピングカート表示画面
	 */
	@RequestMapping
	public String execute(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Order order = new Order();
		
		// ゲストユーザーがランダムIDを持たなければ持たせる処理
		Integer random = 0;
		if(session.getAttribute("randomSessionId") == null){
			random = new Random().nextInt(1147483640)+1000000000;
			order.setUserId(random);
			session.setAttribute("randomSessionId", random);
		}else{				
			order.setUserId((int)session.getAttribute("randomSessionId"));
		}
		if(loginUser != null){
			order.setUserId(loginUser.getUser().getId());
		}
		
		if(viewCartListservice.finfByUserIdAndStatus(order.getUserId(), 0) == null){
			order.setStatus(0);
			order.setTotalPrice(0);
			viewCartListservice.save(order);
		}
		
		order = viewCartListservice.execute(order.getUserId());
		model.addAttribute("orderListSize",order.getOrderItemList().size());
		model.addAttribute("tax", order.getTax());
		model.addAttribute("totalPrice", order.getCalcTotalPrice());
		model.addAttribute("order", order);
		return "cartList";
	}

}