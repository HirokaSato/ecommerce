package jp.co.rakus.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;

/**
 * 注文データをDBから取得するサービスクラス.
 * @author rakus
 *
 */
@Service
@Transactional
public class OrderConfirmationService {
	@Autowired
	private OrderRepository repository;
	
	/**
	 * 注文データを取得する.
	 * @param id
	 * @return
	 */
	public List<Order> findById(Long userId, Integer status){
		
		return repository.findbyId(userId,status);
	}

}
