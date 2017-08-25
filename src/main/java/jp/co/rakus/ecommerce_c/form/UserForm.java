package jp.co.rakus.ecommerce_c.form;

/**
 * ユーザ登録情報を受け取るフォーム.
 * 
 * @author shun.nakano
 *
 */
public class UserForm {
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String email;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String telephone;
	/** パスワード */
	private String password;
	/** 再入力パスワード */
	private String reInputPassword;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReInputPassword() {
		return reInputPassword;
	}
	public void setReInputPassword(String reInputPassword) {
		this.reInputPassword = reInputPassword;
	}
}
