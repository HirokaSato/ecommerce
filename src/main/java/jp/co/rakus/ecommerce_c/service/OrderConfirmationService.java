package jp.co.rakus.ecommerce_c.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.repository.ItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;

/**
 * 注文確認画面表示のためのサービスクラス.
 * @author atsuko.yoshino
 *
 */
@Service
@Transactional
public class OrderConfirmationService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	
	/**
	 * 未注文データを取得する.
	 * @param orderId　取得したい注文データのユーザーId
	 * @return　取得したデータ
	 */
	public Order findByUserIdAndStatus(long userId){
		
		int status = 0;//未注文状態
		return orderRepository.finfByUserIdAndStatus(userId, status);
	}
	
	/**
	 * カートに入った商品を取得する.
	 * @param orderId 注文ID
	 * @return　取得したデータ
	 */
	public List<OrderItem> findByOrderId(long orderId){
		
		return orderItemRepository.findByOrderId(orderId);
	}
	
	/**
	 * カートに入っていた商品の詳細を表示する.
	 * @param itemId 商品のid
	 * @return 取得した商品データ
	 */
	public Item findByItemId(Long itemId){
		
		return itemRepository.load(itemId);
	}
	
	

}
