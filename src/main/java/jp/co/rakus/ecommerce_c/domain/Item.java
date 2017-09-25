package jp.co.rakus.ecommerce_c.domain;

/**
 * 商品情報を表すドメイン.
 *
 * @author ryo.kamiyama
 *
 */
public class Item {

	/** 商品ID */
	private long id;
	/** 商品名 */
	private String name;
	/** 商品説明 */
	private String description;
	/** Mサイズの価格 */
	private Integer priceM;
	/** Lサイズの価格 */
	private Integer priceL;
	/** 画像パス */
	private String imagePath;
	/** 削除フラグ */
	private boolean deleted;
	/** 売上数*/
	private Integer popularity;
	
	/** 属性を受けとり、フィールド属性に詰めるコンストラクタ *//*
	public Item(String name) {
		super();
		this.name = name;
	}*/

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}
	
	public String getStringId(){
		return String.valueOf(id);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
