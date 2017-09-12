package jp.co.rakus.ecommerce_c.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * ユーザ登録情報を受け取るフォーム.
 * 
 * @author shun.nakano
 *
 */
public class UserForm {
	/** ユーザーID */
	private String id;
	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "アドレスが不正です")
	private String email;
	/** 住所 */
	@NotBlank(message = "住所を入力してください")
	private String address;
	/** 電話番号 */
	@Length(min = 10, max = 11, message = "電話番号を入力してください")
	private String telephone;
	/** パスワード */
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	/** 再入力パスワード */
	@NotBlank(message = "確認用パスワードを入力してください")
	private String reInputPassword;

	public String getId() {
		return id;
	}

	/**
	 * 注文IDをLong型に直す.
	 * 
	 * @return Long型の注文ID
	 */
	public Long getLongId() {

		return Long.parseLong(id);
	}

	public void setId(String id) {
		this.id = id;
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
