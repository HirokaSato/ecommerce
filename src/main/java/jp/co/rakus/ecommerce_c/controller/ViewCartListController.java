package jp.co.rakus.ecommerce_c.controller;

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

	@RequestMapping
	public String execute(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Order order = new Order();
		if(loginUser == null){
			order.setUserId((int)session.getAttribute("randomSessionId"));
		}else{
			order.setUserId(loginUser.getUser().getId());
		}
		order = viewCartListservice.execute(order.getUserId());
		model.addAttribute("tax", order.getTax());
		model.addAttribute("totalPrice", order.getCalcTotalPrice());
		model.addAttribute("order", order);
		return "cartList";
	}

}