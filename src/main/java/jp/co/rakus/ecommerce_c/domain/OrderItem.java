package jp.co.rakus.ecommerce_c.domain;

import java.util.List;

/**
 * 注文商品を表すドメイン.
 * 
 * @author rui.toguchi
 *
 */
public class OrderItem {
	
	/** ID　**/
	private Long id;
	/** 商品ID **/
	private Long itemId;
	/** 注文ID **/
	private Long orderId;
	/** 注文数 **/
	private Integer quantity;
	/** サイズ **/
	private Character size;
	/** 商品 **/
//	private Item item;
	/** トッピングリスト**/
	private List orderToppingList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Character getSize() {
		return size;
	}
	public void setSize(Character size) {
		this.size = size;
	}
//	public Item getItem() {
//		return item;
//	}
//	public void setItem(Item item) {
//		this.item = item;
//	}
	public List getOrderToppingList() {
		return orderToppingList;
	}
	public void setOrderToppingList(List orderToppingList) {
		this.orderToppingList = orderToppingList;
	}
	
	
	
}
