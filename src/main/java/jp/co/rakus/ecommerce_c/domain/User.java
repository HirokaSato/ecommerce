package jp.co.rakus.ecommerce_c.domain;

import java.sql.Timestamp;

/**
 * ユーザ情報を表すドメイン.
 * 
 * @author shun.nakano
 *
 */
public class User {
	/** ID */
	private long id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String telephone;
	/** 管理者権限 */
	private boolean manage;
	/** 前回ログイン日時 */
	private Timestamp lastLogin;

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public boolean isManage() {
		return manage;
	}

	public void setManage(boolean manage) {
		this.manage = manage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}
