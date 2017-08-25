package jp.co.rakus.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
import jp.co.rakus.ecommerce_c.domain.Topping;
import jp.co.rakus.ecommerce_c.repository.ItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;
import jp.co.rakus.ecommerce_c.repository.OrderToppingRepository;
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
	private OrderToppingRepository orderToppingRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired ToppingRepository toppingRepository;

	/**
	 * 実行メソッド
	 * @return
	 */
	public Order execute(Integer userId){
		//userId[1]の人のオーダー情報をオブジェクトへ入れる
		Order order = new Order();
		order = orderRepository.findByUserId(userId);
		
		//userId[1]の人のidから注文商品リストを取得
		List<OrderItem> orderItemList = orderItemRepository.findByOrderId(order.getId());
		
		//各注文ピザにトッピングリストを追加する
		for(OrderItem orderItem : orderItemList){
			orderItem.setItem(itemRepository.load(orderItem.getItemId()));
			orderItem.setOrderToppingList(orderToppingRepository.findByOrderItemId(orderItem.getId()));
			//個々の注文商品が持つトッピングリストを取得
			for(int i = 0; i < orderItemList.size(); i++){
				List<OrderTopping> toppingList = orderItem.getOrderToppingList();
				for(OrderTopping orderTopping : toppingList){
					Topping topping = toppingRepository.findByToppingId(orderTopping.getToppingId());
					orderTopping.setTopping(topping);
				}
			}
		}

		//注文商品をorderオブジェクトに詰める
		order.setOrderItemList(orderItemList);	
		
		return order;
	}
	
}



//item_idを基にorder_toppingsテーブルからトッピングリストを取得 -> 
//List<OrderTopping> orderToppingList = orderToppingRepository.findByOrderItemId(orderItem.getId());
//
//orderItemList.get(0).setOrderToppingList(orderToppingList.get(0).getTopping());



