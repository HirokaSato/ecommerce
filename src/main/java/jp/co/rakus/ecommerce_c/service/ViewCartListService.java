package jp.co.rakus.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.Topping;
import jp.co.rakus.ecommerce_c.repository.OrderItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;
import jp.co.rakus.ecommerce_c.repository.ToppingRepository;

/**
 * カート内商品一覧表示の実行クラス.
 * 
 * @author rui.toguchi
 *
 */
@Service
public class ViewCartListService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ToppingRepository ToppingRepository;

	/**
	 * 実行メソッド
	 * @return
	 */
	public Order execute(Integer userId){
		//userId[1]の人のオーダー情報をオブジェクトへ入れる
		Order order = new Order();
		order = orderRepository.findByUserId(userId);
		
		//注文IDを基にorder_itemsテーブルに登録されている商品を取得し、orderオブジェクトに格納
		OrderItem orderItem = new OrderItem();
		List<OrderItem> orderItemlist = orderItemRepository.findByOrderId(order.getId());
		
		//item_idを基にorder_toppingsテーブルからトッピングを取得 -> 
		List<Topping> toppingList = ToppingRepository.findByOrderItemId(orderItem.getId());
	
		//各注文ピザにトッピングリストを追加する
		for(OrderItem piza : orderItemlist){
			piza.setOrderToppingList(toppingList);
		}
		
		//注文商品をorderオブジェクトに詰める
		order.setOrderItemList(orderItemlist);	
		
		return order;
	}
	
}







