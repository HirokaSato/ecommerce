package jp.co.rakus.ecommerce_c.form;

import java.util.List;

/**
 * 注文情報を受け取るフォーム.
 * 
 * @author ryo.kamiyama
 *
 */
public class AddToCartForm {

	/** 商品ID */
	private String itemId;
	/** 商品のサイズ */
	private String size;
	/** トッピングのリスト */
	private List<Integer> toppingList;
	/** 商品の数 */
	private String quantity;

	public String getItemId() {
		return itemId;
	}
	
	public Integer getIntItemId(){
		Integer id = 0;
		try{
			id = Integer.parseInt(this.itemId);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public Integer getIntQuantity(){
		Integer quantity = 0;
		try{
			quantity = Integer.parseInt(this.quantity);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return quantity;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Integer> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
