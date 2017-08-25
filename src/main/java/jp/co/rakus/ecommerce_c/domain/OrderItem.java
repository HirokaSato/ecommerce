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
	private String size;
	/** 商品 **/
	private Item item;
	/** トッピングリスト**/
	private List<OrderTopping> orderToppingList;
	
	//未実装
	public void getSubTotal(){
		
	}
	
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
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
		public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	

	
	
	
}
