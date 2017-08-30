package jp.co.rakus.ecommerce_c.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
import jp.co.rakus.ecommerce_c.domain.Topping;
import jp.co.rakus.ecommerce_c.form.AddToCartForm;
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
	 * 注文情報をDBに追加する.
	 * 
	 * @param order 追加する注文情報
	 * @return 追加時に採番されたIDを含んだOrderクラス
	 */
	public Order saveOrder(Order order){
		 return orderRepository.save(order);
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
	 * 注文商品情報をDBに追加する.
	 * 
	 * @param order 注文情報
	 * @param form 入力情報
	 * @return insert時に採番されたIDを持ったorderItem
	 */
	public OrderItem saveOrderItem(Order order, AddToCartForm form){
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(order.getId());
		orderItem.setItemId(form.getIntItemId());
		orderItem.setSize(form.getSize());
		orderItem.setQuantity(form.getIntQuantity());
		return orderItemRepository.save(orderItem);
	}
	
	/**
	 * 注文トッピング情報をDBに追加する.
	 * 
	 * @param form 入力情報
	 * @param orderItem トッピングされている注文商品
	 */
	public List<OrderTopping> saveOrderToppings(AddToCartForm form, OrderItem orderItem){
		List<OrderTopping> orderToppingList = new ArrayList<>();
		for(Integer orderToppingId : form.getToppingList()){
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setToppingId(orderToppingId);
			orderTopping.setOrderItemId(orderItem.getId());
			orderToppingList.add(orderTopping);
		}
		for(OrderTopping orderTopping : orderToppingList){
			orderToppingRepository.insert(orderTopping);
		}
		return orderToppingList;
	}
	
	/**
	 * 追加された商品ぶんを合わせた合計金額を求める.
	 * 
	 * @param orderId 注文ID
	 * @return 合計金額
	 */
	public Integer getTotalPrice(long orderId, AddToCartForm form){
		// 元の合計金額を取得する
		Integer totalPrice = 0;
		List<Order> orderList = orderRepository.findbyId(orderId, 0);
		totalPrice = orderList.get(0).getTotalPrice();
		
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(form.getIntItemId());
		orderItem.setSize(form.getSize());
		orderItem.setQuantity(form.getIntQuantity());
		
		Integer itemPrice = 0;
		// MかLを判別して、それぞれの価格を取得する
		if(orderItem.getSize().equals("M")){			
			itemPrice = itemRepository.loadItem(orderItem.getItemId()).getPriceM();
		}else if(orderItem.getSize().equals("L")){
			itemPrice = itemRepository.loadItem(orderItem.getItemId()).getPriceL();
		}
		
		List<OrderTopping> orderToppingList = new ArrayList<>();
		for(Integer orderToppingId : form.getToppingList()){
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setToppingId(orderToppingId);
			orderToppingList.add(orderTopping);
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
	
	/**
	 * カートに追加された同一トッピングの商品が、すでにカート内に存在するか調べる<br>
	 * 存在していた場合はその商品の個数を加算する.
	 * 
	 * @param order 注文情報
	 * @param form 入力情報
	 * @return 存在すればtrue、なければfalse
	 */
	public boolean checkDuplicationOrderItem(Order order, AddToCartForm form){
		// 注文された情報をOrderItemとOrderToppingとしてオブジェクトにする
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(form.getIntItemId());
		orderItem.setSize(form.getSize());
		List<OrderTopping> orderToppingList = new ArrayList<>();
		for(Integer orderToppingId : form.getToppingList()){
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setToppingId(orderToppingId);
			orderToppingList.add(orderTopping);
		}
		
		boolean check = false;
		// もともとカートに入っていたOrderItemを取得する
		List<OrderItem> checkOrderItemList = orderItemRepository.findByOrderId(order.getId());
		for(OrderItem checkOrderItem : checkOrderItemList){
			// 注文された商品・サイズが一致するかどうか判別
			if(orderItem.getItemId() == checkOrderItem.getItemId() && orderItem.getSize().equals(checkOrderItem.getSize())){
				// 一致した商品が持っているトッピング情報を取得
				List<OrderTopping> checkOrderToppingList = orderToppingRepository.findByOrderItemId(checkOrderItem.getId());
				// トッピングの数が一致するか判別
				if(orderToppingList.size() == checkOrderToppingList.size()){
					// トッピングの内容が一致していればカウントを増やす
					// 全てが一致した場合はトッピング数とカウントが一致する
					int duplicateCount = 0;
					for(int i = 0; i < orderToppingList.size(); i++){
						if(orderToppingList.get(i).getToppingId() == checkOrderToppingList.get(i).getToppingId()){
							duplicateCount ++;
						}
					}
					if(duplicateCount == orderToppingList.size()){
						check = true;
						this.addOrderItemQuantity(checkOrderItem, form);
					}
				}
			}
		}
		return check;
	}
	
	/**
	 * OrderItemの個数(quantity)を注文された個数ぶん加算する.
	 * 
	 * @param checkOrderItem すでに存在するOrderItem
	 * @param form 入力情報
	 */
	public void addOrderItemQuantity(OrderItem checkOrderItem, AddToCartForm form){
		checkOrderItem.setQuantity(checkOrderItem.getQuantity() + form.getIntQuantity());
		orderItemRepository.save(checkOrderItem);
	}
}
