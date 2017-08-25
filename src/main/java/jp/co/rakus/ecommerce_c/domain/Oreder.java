package jp.co.rakus.ecommerce_c.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Oreder {
	
	/**id*/
	private Long id;
	/**ユーザーID*/
	private Long userId;
	/**状態*/
	private Integer status;
	/**合計金額*/
	private Integer totalPrice;
	/**注文日*/
	private Date orderDate;
	/**宛名*/
	private String destinationName;
	/**宛先Eメール*/
	private String destinationEmail;
	/**宛先郵便番号*/
	private String destinationZipcode;
	/**宛先住所*/
	private String destinationAddress;
	/**宛先TEL*/
	private String destinationTel;
	/**配達時間*/
	private Timestamp deliveryTime;
	/**支払方法*/
	private Integer paymentMethod;
	/**ユーザー*/
	private User user;
	/**注文内容*/
	private List<OrderItem> orderItemList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getDestinationEmail() {
		return destinationEmail;
	}
	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}
	public String getDestinationZipcode() {
		return destinationZipcode;
	}
	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public String getDestinationTel() {
		return destinationTel;
	}
	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
//	//未実装（/**支払税額*/)
//	public int getTax(){
//		
//	
//	}
//	
//	//未実装(/**支払額合計*/)
//	public int getCalcTotalPrice(){
//		
//	}
//	

}
