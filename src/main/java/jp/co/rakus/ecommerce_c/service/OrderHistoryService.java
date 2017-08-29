package jp.co.rakus.ecommerce_c.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
import jp.co.rakus.ecommerce_c.repository.ItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;
import jp.co.rakus.ecommerce_c.repository.OrderToppingRepository;

/**
 * 注文履歴を表示するサービスクラス
 * 
 * @author ryo.kamiyama
 *
 */

@Service
@Transactional
public class OrderHistoryService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 注文履歴をユーザーIDで検索.
	 * 
	 * @param userId ユーザーID
	 *            
	 * @return 取得した注文履歴のデータ、取得できなければnull
	 */
	public List<Order> finfByUserId(long userId) {
		return orderRepository.finfByUserId(userId);

	}

	/**
	 * IDで注文商品を検索する.
	 * 
	 * @param id 検索対象ID
	 *            
	 * @return 照合された注文商品情報、なければnull
	 */
	public OrderItem findById(long id) {
		return orderItemRepository.findById(id);
	}

	/**
	 * 注文商品IDからトッピングリストを取得.
	 * 
	 * @param orderItemId 注文商品ID
	 *           
	 * @return 注文商品に紐づけられたトッピングリスト
	 */

	public List<OrderTopping> findByOrderItemId(long orderItemId) {
		return orderToppingRepository.findByOrderItemId(orderItemId);

	}
	
	/**
	 * 商品IDを元に商品情報を検索する.
	 * 
	 * @param id　商品ID
	 *            
	 * @return item 検索した商品データ
	 */
	
	public Item load(long itemId) {
		return itemRepository.load(itemId);
	}
	
}
