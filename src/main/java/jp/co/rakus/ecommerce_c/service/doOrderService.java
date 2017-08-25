package jp.co.rakus.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;

@Service
@Transactional
public class doOrderService {
	@Autowired
	private OrderRepository repository;
	
	
	
	/**
	 * 注文情報をDBに格納.
	 * @param order 注文情報
	 */
	public void save(Order order){
		
			repository.save(order);

	}
		
		
	}
