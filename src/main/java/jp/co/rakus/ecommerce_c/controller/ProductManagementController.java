package jp.co.rakus.ecommerce_c.controller;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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

	@RequestMapping("/add_item_submit")
	private String submit(@Validated ItemForm form) throws IllegalStateException, IOException {
		Item item = new Item();
		item.setName(form.getName());
		item.setDescription(form.getDescription());
		item.setPriceM(form.getIntPriceM());
		item.setPriceL(form.getIntPriceL());
		// 画像ファイルをサーバにアップロードする
		form.getImage().transferTo(new File(
				"/opt/tomcat8/webapps/media_2017/image/ecommerce201707C/" + form.getImage().getOriginalFilename()));
		// アップロード先のパスをセット
		item.setImagePath("http://172.16.0.16/media_2017/image/ecommerce201707C/" + form.getImage().getOriginalFilename());
		repository.save(item);
		return "redirect:/product_list";
	}
}
