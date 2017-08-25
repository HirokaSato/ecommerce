package jp.co.rakus.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.repository.OrderItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;

/**
 * 注文データをDBから取得するサービスクラス.
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
	
	/**
	 * 注文データを取得する.
	 * @param id
	 * @return
	 */
	public List<Order> findById(Long userId, Integer status){
		
		return orderRepository.findbyId(userId,status);
	}
	
	/**
	 * カートに入っている商品を取得する.
	 * @param orderId　取得したい商品の注文ID
	 * @return　取得したデータ
	 */
	public List<OrderItem> findByOrderId(long orderId){
		
		return orderItemRepository.findByOrderId(orderId);
	}

}
