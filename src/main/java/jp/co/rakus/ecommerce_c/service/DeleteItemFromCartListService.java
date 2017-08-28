package jp.co.rakus.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.repository.OrderItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderToppingRepository;

/**
 * カートリスト情報を操作するクラス.
 * 
 * @author rui.toguchi
 *
 */
@Service
public class DeleteItemFromCartListService {

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	/**
	 * 実行メソッド.
	 */
	public void execute(Integer id,Integer orderItemId){ //(Integer orderItemId,Integer itemId)
		orderToppingRepository.deleteByOrderItemId(orderItemId);
		orderItemRepository.deleteByItemId(id);
	}
	
}
