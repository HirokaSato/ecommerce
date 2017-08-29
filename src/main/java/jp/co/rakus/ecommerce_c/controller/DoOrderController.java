package jp.co.rakus.ecommerce_c.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.domain.LoginUser;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
import jp.co.rakus.ecommerce_c.domain.Topping;
import jp.co.rakus.ecommerce_c.domain.User;
import jp.co.rakus.ecommerce_c.form.AddToCartForm;
import jp.co.rakus.ecommerce_c.form.OrderForm;
import jp.co.rakus.ecommerce_c.service.OrderConfirmationService;
import jp.co.rakus.ecommerce_c.service.doOrderService;

/**
 * 注文を確定するクラス（注文情報を受け取るクラス).
 * 
 * @author rakus
 *
 */
@Controller
@RequestMapping("/doOrderController")
public class DoOrderController {
	@Autowired
	private doOrderService service;
	
	@Autowired
	private OrderConfirmationService orderConfirmationService;
	

	@ModelAttribute
	public AddToCartForm setUpaddToCart(){
		return new AddToCartForm();
	}

	/**
	 * 注文情報を受け取り、DBに格納する.
	 * @param form 注文情報
	 * @return　完了画面
	 * @throws ParseException　Timestamp解析例外
	 */
	@RequestMapping("/order")
	public String insert(@Validated OrderForm form,BindingResult result,@AuthenticationPrincipal LoginUser loginUser,Model model) throws ParseException{
		if(result.hasErrors()){
			return index(loginUser,model);
		}
		Order order = new Order();
		order.setDestinationName(form.getName());
		order.setDestinationEmail(form.getEmail());
		order.setDestinationZipcode(form.getZipcode());
		order.setDeliveryTime(form.getTimeStampDeliveryDate());
		order.setPaymentMethod(form.getIntPaymentMethod());
		order.setUserId(form.getLongUserId());
		order.setTotalPrice(form.getIntTotalPrice());
		order.setId(form.getLongId());
		order.setDestinationAddress(form.getAddress());
		order.setDestinationTel(form.getTelNumber());
		service.save(order);
		
		return "redirect:/orderFinish";
	}
	
	/**
	 * 入力値チェックでエラーが出た場合に入力画面に戻る.
	 * @param userId　ユーザーId
	 * @param loginUser　ログインユーザー情報
	 * @param model　モデル
	 * @return　入力画面
	 */
	@RequestMapping("/index")
	public String index(@AuthenticationPrincipal LoginUser loginUser,Model model){
		User user= loginUser.getUser();
		Order order= orderConfirmationService.findByUserIdAndStatus(user.getId());	
		List<OrderItem> orderItemList = orderConfirmationService.findByOrderId(order.getId());
		List<OrderItem> doOrderItemList = new ArrayList<>();//注文する商品リスト
		for(OrderItem orderItem : orderItemList){
			Item item = orderConfirmationService.findByItemId(orderItem.getItemId());
			orderItem.setItem(item);//商品の詳細格納
			List<OrderTopping> orderToppingList = orderConfirmationService.toppingFindByOrderItemId(orderItem.getId());
			List<OrderTopping> doOrderToppingList = new ArrayList<>();
			for(OrderTopping orderTopping :orderToppingList){
					
					int toppingId = orderTopping.getToppingId();
					Topping topping = orderConfirmationService.toppingFindByToppingId(toppingId);
					orderTopping.setTopping(topping);
					doOrderToppingList.add(orderTopping);
			}
			orderItem.setOrderToppingList(doOrderToppingList);
			}
		
		model.addAttribute("orderItemList",orderItemList);
		order.setOrderItemList(doOrderItemList);
		model.addAttribute("order",order);
		model.addAttribute("tax", order.getTax());
		model.addAttribute("taxIncludedAmount", order.getCalcTotalPrice());
		
		return "orderList";
	}

}
