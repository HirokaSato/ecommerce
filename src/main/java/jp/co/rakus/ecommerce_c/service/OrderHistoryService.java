package jp.co.rakus.ecommerce_c.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.ecommerce_c.domain.Item;
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
 * 注文履歴を表示するサービスクラス.
 * 
 * @author ryo.kamiyama
 *
 */

@Service
@Transactional
public class OrderHistoryService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * 注文履歴を表示する.
	 * 
	 * @param ユーザーID
	 * @return オーダーリスト
	 */
	public List<Order> execute(long userId) {

		// オーダー情報を入手
		List<Order> orderList = orderRepository.finfByUserId(userId);

		for (Order order : orderList) {
			long orderId = order.getId();

			// オーダーIdを元にオーダーアイテム情報を入手
			List<OrderItem> orderItemList = orderItemRepository.findByOrderId(orderId);
			for (OrderItem orderItem : orderItemList) {

				// アイテムIdを元にアイテム情報を入手して、orderItemにセット
				Item item = itemRepository.loadItem(orderItem.getItemId());
				orderItem.setItem(item);

				// オーダーIdを元にオーダートッピング情報を入手。
				long orderItemId = orderItem.getId();
				List<OrderTopping> orderToppingList = orderToppingRepository.findByOrderItemId(orderItemId);
				for (OrderTopping orderTopping : orderToppingList) {

					// トッピングIdを元にトッピング情報を入手して、orderToppingにセット
					Topping topping = toppingRepository.findByToppingId(orderTopping.getToppingId());
					orderTopping.setTopping(topping);
				}
				orderItem.setOrderToppingList(orderToppingList);
				orderItem.setSubTotalPrice(orderItem.getSubTotal());
			}
			order.setOrderItemList(orderItemList);
		}
		return orderList;
	}
}
