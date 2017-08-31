package jp.co.rakus.ecommerce_c.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.domain.LoginUser;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
import jp.co.rakus.ecommerce_c.domain.Topping;
import jp.co.rakus.ecommerce_c.domain.User;
import jp.co.rakus.ecommerce_c.form.OrderForm;
import jp.co.rakus.ecommerce_c.service.OrderConfirmationService;

/**
 * 注文確認画面を表示するコントローラー.
 * 
 * @author atsuko.yoshino
 *
 */
@Controller
@RequestMapping("/OrderConfirmationController")
public class OrderConfirmationController {
	@Autowired
	private OrderConfirmationService orderConfirmationService;

	@ModelAttribute
	public OrderForm setUp() {
		return new OrderForm();
	}

	/**
	 * 注文確認画面を表示する.
	 * 
	 * @param userId
	 *            ログインユーザーのId
	 * @param model
	 *            モデル
	 * @return 注文確認画面
	 */
	@RequestMapping("/")
	public String index(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		User user = loginUser.getUser();
		model.addAttribute("user", user);
		Order order = orderConfirmationService.findByUserIdAndStatus(user.getId());
		if (order == null) {
			return "redirect:/viewCartList";
		}
		List<OrderItem> orderItemList = orderConfirmationService.findByOrderId(order.getId());
		if (orderItemList.size() == 0) {
			return "redirect:/viewCartList";
		}
		List<OrderItem> doOrderItemList = new ArrayList<>();// 注文する商品リスト(入れなおし用)
		for (OrderItem orderItem : orderItemList) {
			Item item = orderConfirmationService.findByItemId(orderItem.getItemId());
			orderItem.setItem(item);// 商品の詳細格納

			List<OrderTopping> orderToppingList = orderConfirmationService.toppingFindByOrderItemId(orderItem.getId());
			List<OrderTopping> doOrderToppingList = new ArrayList<>();// 注文されたトッピングリスト（入れ直し用)
			for (OrderTopping orderTopping : orderToppingList) {

				int toppingId = orderTopping.getToppingId();
				Topping topping = orderConfirmationService.toppingFindByToppingId(toppingId);
				orderTopping.setTopping(topping);
				doOrderToppingList.add(orderTopping);
			}

			orderItem.setOrderToppingList(doOrderToppingList);
			orderItem.setSubTotalPrice(orderItem.getSubTotal());
		}

		model.addAttribute("orderItemList", orderItemList);
		order.setOrderItemList(doOrderItemList);
		model.addAttribute("order", order);
		model.addAttribute("tax", order.getTax());
		model.addAttribute("taxIncludedAmount", order.getCalcTotalPrice());
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());// 過去日で配達日を指定しないようにするため。
		model.addAttribute("today", today);
		LocalDate nowLocalDate = LocalDate.now().plusMonths(1);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String limitDate = nowLocalDate.format(format);
		model.addAttribute("limitDate", limitDate);

		return "orderList";
	}

	/**
	 * カート画面にもどる.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("returnCart")
	public String returnCart(Model model) {
		return "cartList";
	}

}
