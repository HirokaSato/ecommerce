package jp.co.rakus.ecommerce_c.form;

import java.sql.Timestamp;
import java.text.ParseException;

/**
 * 注文情報を受け取るフォーム.
 * @author rakus
 *
 */
public class OrderForm {
	
	/**宛名名前*/
	private String name;
	/**宛先メールアドレス*/
	private String email;
	/**宛先郵便番号*/
	private String zipcode;
	/**宛名*/
	private String address;
	/**宛先電話番号*/
	private String telNumber;
	/**配達日*/
	private String deliveryDate;
	/**配達時間*/
	private String deliveryTime;
	/**お支払い方法*/
	private String paymentMethod;
	/**ユーザーID*/
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * ユーザーのIDをLongに変換.
	 * @return Long型ユーザーId
	 */
	public Long getLongUserId(){
		
		return Long.parseLong(userId);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	/**	DeliveryDateとTimeをDateに直す*/
	public Timestamp getTimeStampDeliveryDate() throws ParseException{
		String deliverydate = deliveryDate+deliveryTime;
		Timestamp timestamp = Timestamp.valueOf(deliverydate);
		return timestamp;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		
		this.paymentMethod = paymentMethod;
	}
	
	public Integer getIntPaymentMethod(){
		
		return Integer.parseInt(paymentMethod);
	}

	
	

}
