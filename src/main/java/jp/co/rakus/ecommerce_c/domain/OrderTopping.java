package jp.co.rakus.ecommerce_c.domain;

/**
 * 注文トッピングを表すドメイン.
 * 
 * @author rui.toguchi
 *
 */
public class OrderTopping {

	/** ID **/
	private Integer id;
	/** 注文トッピングID **/
	private Integer toppingId;
	/** 注文商品ID **/
	private long orderItemId;
	/** トッピング **/
	private Topping topping;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getToppingId() {
		return toppingId;
	}
	public void setToppingId(Integer toppingId) {
		this.toppingId = toppingId;
	}
	public Topping getTopping() {
		return topping;
	}
	public void setTopping(Topping topping) {
		this.topping = topping;
	}
	public long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}
	
	
}
