package jp.co.rakus.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.repository.OrderItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;
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
	
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 実行メソッド.
	 */
	
						//　　ﾕｰｻﾞID　　      注文商品ID　　　　　　　　　  　小計  
	public void execute(Integer userId,Integer id,Integer subTotalPrice){ 
		
		//orderテーブルの金額合計を取得
		Order order = new Order();
		order = orderRepository.findByUserId(userId);
		int totalPrice = order.getTotalPrice();
		
		//金額合計から小計を引く
		totalPrice -= subTotalPrice;
		
		//削除後の金額をセット
		order.setTotalPrice(totalPrice);
		
		//トッピング削除
		orderToppingRepository.deleteByOrderItemId(id);
		//商品削除
		orderItemRepository.deleteByItemId(id);

		//orderテーブルの情報をアップデート
		orderRepository.save(order);
	}
	
}
