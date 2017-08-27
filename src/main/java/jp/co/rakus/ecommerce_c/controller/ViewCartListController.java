package jp.co.rakus.ecommerce_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.service.ViewCartListService;

/**
 * カート内の商品を表示するコントローラークラス.
 * 
 * @author rui.toguchi
 *
 */
@Controller
@RequestMapping("/veiwCartList")
public class ViewCartListController {
 
 @Autowired
 private ViewCartListService viewCartListservice;
 
 @RequestMapping("/execute")
 public String execute(Integer userId, Model model){
  Order order = new Order();
  order = viewCartListservice.execute(1); 
  model.addAttribute("subTotal",viewCartListservice.getSubTotalPrice());
  model.addAttribute("tax",order.getTax());
  model.addAttribute("totalPrice",order.getCalcTotalPrice());
  model.addAttribute("order",order);
  return "cartList";
 }
 
}