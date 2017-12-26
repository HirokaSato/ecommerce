package jp.co.rakus.ecommerce_c.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
import jp.co.rakus.ecommerce_c.domain.User;
import jp.co.rakus.ecommerce_c.repository.OrderItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;
import jp.co.rakus.ecommerce_c.repository.OrderToppingRepository;

/**
 * ログイン時にカートの内容を操作するサービスクラス.
 * 
 * @author shun.nakano
 *
 */
/**
 * @author shun.nakano
 *
 */
/**
 * @author shun.nakano
 *
 */
@Service
public class LoginOrderChangeService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	@Autowired
	private HttpSession session;
	
	/**
	 * ゲスト状態のカート(Order)の中身があればユーザ情報を書き換える.
	 * 
	 * @param user
	 */
	public void execute(User user){
		Order order = new Order();
		// ゲスト状態のカートの中身があればユーザ情報を書き換える
		order = orderRepository.finfByUserIdAndStatus((int)session.getAttribute("randomSessionId"), 0);
		if(order != null){
			Order userOrder = orderRepository.finfByUserIdAndStatus(user.getId(), 0);
			// ログインユーザが未注文状態のOrderをもたなければ情報の上書き、もっていれば追加する
			if(userOrder == null){
				order.setUserId(user.getId());
				orderRepository.save(order);
			}else{
				if(order.getTotalPrice() != 0){
				this.addCartFromGuestCart(order, userOrder);
				}
			}
		}
	}
	
	/**
	 * ユーザが持つ未注文状態の注文に、ゲスト時のカート内容を追加する.
	 */
	public void addCartFromGuestCart(Order guestOrder, Order userOrder){
		Integer userOrderTotalPrice = userOrder.getTotalPrice();
		
		// ゲスト時のユーザがもっていたOrderを取得する
		Integer guestCartPrice = guestOrder.getTotalPrice();
		List<OrderItem> guestOrderItemList = orderItemRepository.findByOrderId(guestOrder.getId());
		
		// 取得したOrderItemのオーダーIDをログインユーザの未注文Orderのものに書き換える
		for(OrderItem guestOrderItem : guestOrderItemList){
			if(!this.checkDuplicationOrderItem(userOrder, guestOrderItem)){			
				guestOrderItem.setOrderId(userOrder.getId());
				orderItemRepository.save(guestOrderItem);
			}
		}
		
		// 合計金額に追加して、データベースを更新する
		userOrderTotalPrice += guestCartPrice;
		userOrder.setTotalPrice(userOrderTotalPrice);
		orderRepository.save(userOrder);
		
		// ゲストがもっていたOrderを削除
		orderRepository.deleteByOrderId(guestOrder.getId());
	}
	
	/**
	 * カートごとに完全に重複しているOrderItemがあれば、新規に追加ではなくQuantityを加算する.
	 * 
	 * @param userOrder ログインユーザの未注文情報
	 * @param guestOrderItem ゲスト状態のカートに入っていた注文商品情報
	 * @return 重複していればtrue、なければfalse
	 */
	public boolean checkDuplicationOrderItem(Order userOrder, OrderItem guestOrderItem){
		boolean check = false;

		List<OrderItem> userOrderItemList = orderItemRepository.findByOrderId(userOrder.getId());
		for(OrderItem userOrderItem : userOrderItemList){
			if(userOrderItem.getItemId()==guestOrderItem.getItemId() && userOrderItem.getSize().equals(guestOrderItem.getSize())){
				List<OrderTopping> userOrderToppingList = orderToppingRepository.findByOrderItemId(userOrderItem.getId());
				List<OrderTopping> guestOrderToppingList = orderToppingRepository.findByOrderItemId(guestOrderItem.getId());
				if(userOrderToppingList.size()==guestOrderToppingList.size()){
					int duplicateCount = 0;
					if(userOrderToppingList.size() != 0){
						for(int i = 0; i < userOrderToppingList.size(); i++){
							if(userOrderToppingList.get(i).getToppingId()==guestOrderToppingList.get(i).getToppingId()){
								duplicateCount ++;
							}
						}				
					}
					if(duplicateCount==userOrderToppingList.size()){
						check=true;
						userOrderItem.setQuantity(userOrderItem.getQuantity()+guestOrderItem.getQuantity());
						orderItemRepository.save(userOrderItem);
					}
				}
			}
		}
		return check;
	}
}
