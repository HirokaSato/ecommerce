package jp.co.rakus.ecommerce_c.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;

@Service
@Transactional
public class OrderHistoryService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 注文履歴をユーザーIDで検索.
	 * @param userId ユーザーID
	 * @return　取得した注文履歴のデータ、取得できなければnull
	 */	
	public List<Order> finfByUserId(long userId) {
		return orderRepository.finfByUserId(userId);

	}
}
