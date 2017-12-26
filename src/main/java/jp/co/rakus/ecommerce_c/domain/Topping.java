package jp.co.rakus.ecommerce_c.domain;

/**
 * トッピング情報を表すドメイン.
 * 
 * @author ryo.kamiyama
 *
 */
public class Topping {

	/** 商品ID */
	private Integer id;
	/** 商品名 */
	private String name;
	/** Mサイズの価格 */
	private Integer priceM;
	/** Lサイズの価格 */
	private Integer priceL;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriceM() {
		return priceM;
	}

	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	public Integer getPriceL() {
		return priceL;
	}

	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}

}
