package jp.co.rakus.ecommerce_c.domain;

import java.util.List;

/**
 * 注文商品を表すドメイン.
 * 
 * @author rui.toguchi
 *
 */
public class OrderItem {

	/** ID **/
	private long id;
	/** 商品ID **/
	private long itemId;
	/** 注文ID **/
	private long orderId;
	/** 注文数 **/
	private Integer quantity;
	/** サイズ **/
	private String size;
	/** 商品 **/
	private Item item;
	/** トッピングリスト **/
	private List<OrderTopping> orderToppingList;
	
	private Integer subTotalPrice;

	//テスト用
	public OrderItem(int i, int j, int k, String string) {
	}
	public OrderItem(){
		
	}


	/**
	 * 小計を取得するメソッド.
	 */
	public Integer getSubTotal() {
		int subtotal = 0;
		
		//ピザ本体の小計の計算
		if (this.size.equals("M")) {
			subtotal += this.item.getPriceM() * this.quantity;
		} else if (this.size.equals("L")) {
			subtotal += this.item.getPriceL() * this.quantity;
		}
		
		//トッピング代金を小計に加える
		for (OrderTopping orderTopping : orderToppingList) {
			if (this.size.equals("M")) {
				subtotal += orderTopping.getTopping().getPriceM() * this.quantity;
			} else if (this.size.equals("L")) {
				subtotal += orderTopping.getTopping().getPriceL() * this.quantity;
			}
		}
		return subtotal;
	}
	
	

	public Integer getSubTotalPrice() {
		return subTotalPrice;
	}



	public void setSubTotalPrice(Integer subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
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