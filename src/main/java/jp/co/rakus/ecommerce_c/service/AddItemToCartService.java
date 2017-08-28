package jp.co.rakus.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.Item;
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
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ToppingRepository toppingRepository;
	
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
	
	/**
	 * カートに追加された商品の値段をこれまでの合計金額に加算する.
	 * 
	 * @param orderItem 追加された商品
	 * @return 追加分を加算した合計金額
	 */
	public Integer getTotalPrice(OrderItem orderItem){
		Item item = itemRepository.load(orderItem.getItemId());
		List<OrderTopping> orderToppingList = orderToppingRepository.findByOrderItemId(orderItem.getId());
		Integer itemPrice = 0;
		
		// 追加された商品の価格を取得
		if(orderItem.getSize().equals('M')){
			itemPrice = item.getPriceM();
		}else if(orderItem.getSize().equals('L')){
			itemPrice = item.getPriceL();
		}
		
		// トッピング分の価格を加算
		for(OrderTopping orderTopping : orderToppingList){
			Topping topping = toppingRepository.findByToppingId(orderTopping.getToppingId());
			if(orderItem.getSize().equals('M')){
				itemPrice += topping.getPriceM();
			}else if(orderItem.getSize().equals('L')){
				itemPrice += topping.getPriceL();
			}
		}
		
		// OrderItemオブジェクトのOrderIdを利用してカートの合計金額を取得
		Integer totalPrice = 0;
		Order order = orderRepository.findByUserId(orderItem.getOrderId());
		// カートが存在するならば、まずその合計金額を取得する
		if(order != null){
			totalPrice = order.getTotalPrice();
		}
		totalPrice += itemPrice;		
		return totalPrice;
	}
}
