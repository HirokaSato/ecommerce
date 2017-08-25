package jp.co.rakus.ecommerce_c.service;

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
public class OrderConfirmation {
	@Autowired
	private OrderRepository repository;
	
	/**
	 * 注文データを取得する.
	 * @param id
	 * @return
	 */
	public Order findById(Integer id){
		
		return repository.findbyId(id);
	}

}
