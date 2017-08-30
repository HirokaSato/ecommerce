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
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ToppingRepository toppingRepository;
	
	/**
	 * 注文情報を追加する.
	 * 
	 * @param order 追加する注文情報
	 * @return 追加時に採番されたIDを含んだOrderクラス
	 */
	public Order saveOrder(Order order){
		 return orderRepository.save(order);
	}
	
	/**
	 * 注文された商品を追加する.
	 * 
	 * @param orderItem 注文された商品情報
	 * @return 追加したときに採番されたid情報を加えたOrderItem
	 */
	public OrderItem saveOrderItem(OrderItem orderItem){
		return orderItemRepository.insert(orderItem);
	}
	
	/**
	 * 注文されたトッピング情報を追加する.
	 * 
	 * @param orderToppingList トッピング情報リスト
	 */
	public void saveOrderToppings(List<OrderTopping> orderToppingList){
		for(OrderTopping orderTopping : orderToppingList){
			orderToppingRepository.insert(orderTopping);
		}
	}
	
	/**
	 * 注文データをユーザーIDと状態で検索.
	 * 
	 * @param userId ユーザーID
	 * @param status　取引状態
	 * @return　取得した注文データ、取得できなければnull
	 */
	public Order findByUserIdAndStatus(long userId, Integer status){
		return orderRepository.finfByUserIdAndStatus(userId, status);
	}
	
	/**
	 * 追加された商品ぶんを合わせた合計金額を求める.
	 * 
	 * @param orderId 注文ID
	 * @return 合計金額
	 */
	public Integer getTotalPrice(long orderId, long orderItemId, List<OrderTopping> orderToppingList){
		// 元の合計金額を取得する
		Integer totalPrice = 0;
		List<Order> orderList = orderRepository.findbyId(orderId, 0);
		totalPrice = orderList.get(0).getTotalPrice();
		
		// MかLを判別して、それぞれの価格を取得する
		OrderItem orderItem = orderItemRepository.findById(orderItemId);
		Integer itemPrice = 0;
		if(orderItem.getSize().equals("M")){			
			itemPrice = itemRepository.loadItem(orderItem.getItemId()).getPriceM();
		}else if(orderItem.getSize().equals("L")){
			itemPrice = itemRepository.loadItem(orderItem.getItemId()).getPriceL();
		}
		
		// トッピング分を商品価格に加算する
		for(OrderTopping orderTopping : orderToppingList){
			Topping topping = toppingRepository.findByToppingId(orderTopping.getToppingId());
			if(orderItem.getSize().equals("M")){			
				itemPrice += topping.getPriceM();
			}else if(orderItem.getSize().equals("L")){
				itemPrice += topping.getPriceL();
			}
		}
		
		// 商品数を乗算する
		itemPrice *= orderItem.getQuantity();
		
		totalPrice += itemPrice;
		return totalPrice;
	}
}
