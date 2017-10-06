package jp.co.rakus.ecommerce_c.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.form.ItemForm;
import jp.co.rakus.ecommerce_c.repository.ItemRepository;

@Controller
@RequestMapping
public class ProductManagementController {

	@Autowired
	private ItemRepository repository;

	@ModelAttribute
	public ItemForm setItemForm() {
		return new ItemForm();
	}

	@RequestMapping("/product_list")
	public String manage(Model model) {
		List<Item> pizzaList = repository.findAllItem();
		model.addAttribute("pizzaList", pizzaList);
		return "allProduct";
	}

	@ResponseBody
	@RequestMapping("/SearchAllItem")
	public List<Item> searchItemByAjax(String searchWord) {
		return repository.findAllItem();
	}

	@RequestMapping("/add_item")
	public String add() {
		return "addItem";
	}

	/*
	 * @RequestMapping("/add_item_submit") private String submit(@Validated
	 * ItemForm form, Model model) throws IllegalStateException, IOException {
	 * Item item = new Item(); item.setName(form.getName());
	 * item.setDescription(form.getDescription());
	 * item.setPriceM(form.getIntPriceM()); item.setPriceL(form.getIntPriceL());
	 * item.setPopularity(form.getIntPopularity()); // 画像ファイルをサーバにアップロードする
	 * form.getImage().transferTo(new File(
	 * "/opt/tomcat8/webapps/media_2017/image/ecommerce201707C/" +
	 * form.getImage().getOriginalFilename())); // アップロード先のパスをセット
	 * item.setImagePath(
	 * "http://172.16.0.16/media_2017/image/ecommerce201707C/" +
	 * form.getImage().getOriginalFilename()); repository.save(item); return
	 * manage(model); }
	 */

	// 一括追加
	@ResponseBody
	@RequestMapping("/addItemByAjax")
	public String addItemByAjax(ItemForm item_FormData) throws IllegalStateException, IOException {
		Item item = new Item();
		item.setName(item_FormData.getName());
		item.setDescription(item_FormData.getDescription());
		item.setPriceM(item_FormData.getIntPriceM());
		item.setPriceL(item_FormData.getIntPriceL());
		item.setPopularity(item_FormData.getIntPopularity());
		item.setDeleted(false);
		// 画像ファイルをサーバにアップロードする
		item_FormData.getImage().transferTo(new File("/opt/tomcat8/webapps/media_2017/image/ecommerce201707C/"
				+ item_FormData.getImage().getOriginalFilename()));
		// アップロード先のパスをセット
		item.setImagePath("http://172.16.0.16/media_2017/image/ecommerce201707C/"
				+ item_FormData.getImage().getOriginalFilename());
		repository.save(item);
		return "{\"message\":\"登録完了\"}";
	}

	// 一括削除
	@ResponseBody
	@RequestMapping("/deleteItemByAjax")
	public String deleteItemByAjax(String id) throws IllegalStateException, IOException {
		repository.deleteById(Integer.parseInt(id));
		return "{\"message\":\"削除完了\"}";
	}

	// 一括更新
	@ResponseBody
	@RequestMapping("/editItemByAjax")
	public String editItemByAjax(ItemForm edit_FormData) throws IllegalStateException, IOException {
		Item editItem = repository.loadItem(edit_FormData.getIntId());
		if (!edit_FormData.getName().isEmpty()) {
			editItem.setName(edit_FormData.getName());
		}
		if (!edit_FormData.getPriceM().isEmpty()) {
			editItem.setPriceM(edit_FormData.getIntPriceM());
		}
		if (!edit_FormData.getPriceL().isEmpty()) {
			editItem.setPriceL(edit_FormData.getIntPriceL());
		}
		/*
		 * if(!edit_FormData.getImage().isEmpty()){
		 * edit_FormData.getImage().transferTo(new
		 * File("/opt/tomcat8/webapps/media_2017/image/ecommerce201707C/" +
		 * edit_FormData.getImage().getOriginalFilename()));
		 */
		// アップロード先のパスをセット
		editItem.setImagePath("http://172.16.0.16/media_2017/image/ecommerce201707C/"
				+ edit_FormData.getImage().getOriginalFilename());

		// 情報更新
		repository.update(editItem);
		return "{\"message\":\"更新完了\"}";
	}

}
