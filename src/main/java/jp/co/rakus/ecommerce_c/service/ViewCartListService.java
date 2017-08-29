package jp.co.rakus.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
import jp.co.rakus.ecommerce_c.domain.Topping;
import jp.co.rakus.ecommerce_c.repository.ItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderItemRepository;
import jp.co.rakus.ecommerce_c.repository.OrderRepository;
import jp.co.rakus.ecommerce_c.repository.OrderToppingRepository;
import jp.co.rakus.ecommerce_c.repository.ToppingRepository;

/**
 * カート内商品一覧表示の実行クラス.
 * 
 * @author rui.toguchi
 *
 */
@Service
public class ViewCartListService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	ToppingRepository toppingRepository;

	private int subTotalPrice = 0;

	/**
	 * 実行メソッド
	 * 
	 * @return
	 */
	public Order execute(long userId) {
		// オーダー情報をオブジェクトへ入れる
		Order order = new Order();
		order = orderRepository.finfByUserIdAndStatus(userId, 0);

		// idから注文商品リストを取得
		List<OrderItem> orderItemList = orderItemRepository.findByOrderId(order.getId());

		// 各注文ピザにトッピングリストを追加する
		for (OrderItem orderItem : orderItemList) {
			orderItem.setItem(itemRepository.load(orderItem.getItemId()));
			orderItem.setOrderToppingList(orderToppingRepository.findByOrderItemId(orderItem.getId()));//getId()かな
			// 個々の注文商品が持つトッピングリストを取得
			for (int i = 0; i < orderItemList.size(); i++) {
				List<OrderTopping> toppingList = orderItem.getOrderToppingList();
				for (OrderTopping orderTopping : toppingList) {
					Topping topping = toppingRepository.findByToppingId(orderTopping.getToppingId());
					orderTopping.setTopping(topping);
				}
			}
			orderItem.setSubTotalPrice(orderItem.getSubTotal());
			System.out.println(orderItem.getSubTotal()+"あああああああああああああ");
		}

		// 注文商品をorderオブジェクトに詰める
		order.setOrderItemList(orderItemList);
		return order;
	}

	public int getSubTotalPrice() {
		return subTotalPrice;
	}

	public void setSubTotalPrice(int subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}
	
	/**
	 * 注文データをユーザーIDと状態で検索.
	 * @param userId ユーザーID
	 * @param status　取引状態
	 * @return　取得した注文データ、取得できなければnull
	 */
	public Order finfByUserIdAndStatus(long userId, int status){
		return orderRepository.finfByUserIdAndStatus(userId, status);
	}
	
	/**
	 * userIdが対応するOrderが存在しない場合に新規に追加する.
	 * 
	 * @param order 追加する情報
	 * @return 追加されたIDをもったOrder情報
	 */
	public Order save(Order order){
		return orderRepository.save(order);
	}

}