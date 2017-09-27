package jp.co.rakus.ecommerce_c.form;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class ItemForm {
	/** 商品名 */
	@NotBlank(message = "商品名を入力してください")
	private String name;
	/** 商品説明 */
	@NotBlank(message = "説明文を入れてください")
	private String description;
	/** Mサイズの価格 */
	private String priceM;
	/** Lサイズの価格 */
	private String priceL;
	/** 画像パス */
	private MultipartFile image;
	/** 削除フラグ */
	private boolean deleted;
	/** 売上数 */
	private String popularity;
	
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
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

	public String getPriceM() {
		return priceM;
	}

	public void setPriceM(String priceM) {
		this.priceM = priceM;
	}
	
	public Integer getIntPriceM(){
		Integer priceM = 0;
		try{
			priceM = Integer.parseInt(this.priceM);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return priceM;
	}

	public String getPriceL() {
		return priceL;
	}

	public void setPriceL(String priceL) {
		this.priceL = priceL;
	}
	
	public Integer getIntPriceL(){
		Integer priceL = 0;
		try{
			priceL = Integer.parseInt(this.priceL);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return priceL;
	}

	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getPopularity() {
		return popularity;
	}

	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}
	
	public Integer getIntPopularity(){
		Integer popularity = 0;
		try{
			popularity = Integer.parseInt(this.popularity);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		return popularity;
	}
}
