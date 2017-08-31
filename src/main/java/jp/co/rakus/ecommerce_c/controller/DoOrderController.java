package jp.co.rakus.ecommerce_c.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
import jp.co.rakus.ecommerce_c.service.DoOrderService;
import jp.co.rakus.ecommerce_c.service.OrderConfirmationService;

/**
 * 注文を確定するクラス（注文情報を受け取るクラス).
 * 
 * @author atsuko.yoshino
 *
 */
@Controller
@RequestMapping("/doOrderController")
public class DoOrderController {
	@Autowired
	private DoOrderService service;
	
	@Autowired
	private OrderConfirmationService orderConfirmationService;
	
	@Autowired
	private MailSender mailSender;

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
		boolean errors = false;
		if(result.hasErrors()){
			errors = true;
		}
		
		try{
			LocalDateTime nowLocalDateTime = LocalDateTime.now();
			LocalDateTime conditionDateTime = nowLocalDateTime.plusHours(1);
			String[] timeList = form.getDeliveryTime().split(":");
			int integerDeliveryTimeList[] = new int[3];
			for(int i =0;i<3;i++){
				integerDeliveryTimeList[i] = Integer.parseInt(timeList[i]);
			}
			String[] dateList = form.getDeliveryDate().split("-");
			int integerDeliveryDateList[] = new int[3];
			for(int i =0;i<3;i++){
				integerDeliveryDateList[i] = Integer.parseInt(dateList[i]);
			}
			LocalDateTime deliveryDateTime = LocalDateTime.of(integerDeliveryDateList[0], integerDeliveryDateList[1], integerDeliveryDateList[2], integerDeliveryTimeList[0],integerDeliveryTimeList[1],integerDeliveryTimeList[2]);
			if(deliveryDateTime.isBefore(conditionDateTime)){
				model.addAttribute("errors", "現在から１時間以降の日時を選択してください。");
				errors =true;
			}
		}catch(NullPointerException e){
			
			errors = true;
		}
		
		try{
			Long.parseLong(form.getTelNumber());
		}catch(NumberFormatException e){
			model.addAttribute("errors2", "数字を入れてください");
			errors = true;
		}
		
		try{
			Integer.parseInt(form.getZipcode());
		}catch(NumberFormatException e){
			model.addAttribute("errors3", "７桁の数字を入れてください");
			errors = true;
		}
		
		if(errors == true){
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
		
		return "redirect:/doOrderController/finish";
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
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		model.addAttribute("today",today);//過去日指定防止
		LocalDate nowLocalDate = LocalDate.now().plusMonths(1);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String limitDate = nowLocalDate.format(format);
		model.addAttribute("limitDate", limitDate);//一カ月以内のみ注文可
	
		
		// 注文完了メールを送信する(機能していないのでコメントアウトします)
//		sendMail(order); 
		return "orderList";
	}
	
	/**
	 * 注文完了画面へ遷移.
	 * @return 注文完了画面
	 */
	@RequestMapping("/finish")
	public String finish(){
		
		return "orderFinish";
	}
	
	/**
	 * 注文完了確認メールを送信する.<br>
	 * by shun.nakano
	 * 
	 * @param order 注文情報
	 */
	public void sendMail(Order order){
		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setFrom("nakanon.41@gmail.com");
		msg.setTo(order.getDestinationEmail());
		msg.setSubject("ラクラクピザ：ご注文ありがとうございます！");
		msg.setText(order.getDestinationName()+"様\n"
				  + "ご注文ありがとうございます！\n"
				  + "--------------------\n"
				  + "【ご注文情報】\n"
				  + "お名前："+order.getDestinationName()+"\n"
				  + "ご住所："+order.getDestinationAddress());
		mailSender.send(msg);
	}
}
