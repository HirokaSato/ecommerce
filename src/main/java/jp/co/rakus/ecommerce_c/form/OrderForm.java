package jp.co.rakus.ecommerce_c.form;

import java.sql.Timestamp;
import java.text.ParseException;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 注文情報を受け取るフォーム.
 * @author atsuko.yoshino
 *
 */
public class OrderForm {
	
	/**宛名名前*/
	@NotEmpty(message="名前入力は必須です")
	private String name;
	/**宛先メールアドレス*/
	@NotBlank(message="メールアドレスを入力してください")
	@Email(message="メールアドレスの入力が不正です")
	private String email;
	/**宛先郵便番号*/
	@NotNull(message="郵便番号を入力してください")
	@Length(min=7,max=7,message="郵便番号を入力してください")
	private String zipcode;
	/**宛先住所*/
	@NotEmpty(message="住所入力は必須です")
	private String address;
	/**宛先電話番号*/
	@NotNull(message="電話番号を入力してください")
	@Length(min=10,max=11,message="電話番号を入力してください")
	private String telNumber;
	/**配達日*/
	@NotEmpty(message="配達希望日を入力してください")
	private String deliveryDate;
	/**配達時間*/
	@NotEmpty(message="配達希望時間を入力してください")
	private String deliveryTime;
	/**お支払い方法*/
	@NotEmpty(message="お支払方法を選択してください")
	private String paymentMethod;
	/**ユーザーID*/
	private String userId;
	/**注文ID*/
	private String id;
	/**合計金額*/
	private String totalPrice;
	
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * 合計金額をInt型に直す.
	 * @return 合計金額
	 */
	public Integer getIntTotalPrice(){
		return Integer.parseInt(totalPrice);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 注文IDをLong型に直す.
	 * @return Long型の注文ID
	 */
	public Long getLongId(){
		
		return Long.parseLong(id);
	}
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
	
	/**	DeliveryDateとTimeをTimestampに直す*/
	public Timestamp getTimeStampDeliveryDate() throws ParseException{
		String deliverydate = this.deliveryDate+" "+this.deliveryTime;
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
