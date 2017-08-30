package jp.co.rakus.ecommerce_c.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;

/**
 * 注文するために必要なデータを操作する.
 * @author atsuko.yoshino	
 *
 */
@Service
@Transactional
public class DoOrderService {
	@Autowired
	private OrderRepository repository;
	
	/**
	 * 注文情報をDBに格納.
	 * @param order 注文情報
	 */
	public void save(Order order){
			//本日の日付（注文日)
			Date date = new Date();
			order.setOrderDate(date);
			//支払方法からの状態変更
			if(order.getPaymentMethod()==1){
				order.setStatus(1);//未入金
			}else if(order.getPaymentMethod()==2){
				order.setStatus(2);//入金済み
			}else{
				order.setStatus(4);//キャンセル
			}
			
			repository.save(order);
	}
}
