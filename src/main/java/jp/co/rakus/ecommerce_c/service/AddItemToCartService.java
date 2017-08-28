package jp.co.rakus.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
import jp.co.rakus.ecommerce_c.repository.OrderItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;
import jp.co.rakus.ecommerce_c.repository.OrderToppingRepository;

/**
 * 商品をカートに入れる処理を行うサービス.
 * 
 * @author shun.nakano
 *
 */
@Service
public class AddItemToCartService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository oderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	/**
	 * 受け取った商品情報をそれぞれのテーブルに追加する.
	 * 
	 * @param order
	 * @param orderItem
	 */
	public void execute(Order order, OrderItem orderItem){
		// このメソッドは未テストです
		orderRepository.save(order);
		oderItemRepository.insert(orderItem);
		for(OrderTopping orderTopping : orderItem.getOrderToppingList()){
			orderToppingRepository.insert(orderTopping);
		}
	}
}
