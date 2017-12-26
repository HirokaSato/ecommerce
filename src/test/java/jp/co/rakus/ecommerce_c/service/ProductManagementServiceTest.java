package jp.co.rakus.ecommerce_c.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.rakus.ecommerce_c.domain.Item;

/**
 * 商品管理に関する、テストクラスの実装.
 * 
 * @author hiroka.sato
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductManagementServiceTest {
	/** テストしたいProductManagementサービスクラスを有効化 */
	@Autowired
	ProductManagementService service;

	@Test
	public void すべての商品情報が価格の昇順に取得していること() {
		List<Item> ItemList = service.findAll();
		assertThat("表示されていません", ItemList.size(), is(21));
		assertThat("価格が昇順ではありません", ItemList.get(2).getPriceM() < ItemList.get(3).getPriceM(), is(true));
		assertThat("価格が昇順ではありません", ItemList.get(5).getPriceM() < ItemList.get(9).getPriceM(), is(true));
	}

	@Test
	public void 新商品を追加する() {
		List<Item> ItemList = service.findAll();
		// List<Item> articleList = service.findAll();
		Item item = new Item();
		item.setId(19);
		item.setName("たこやき");
		item.setImagePath("画像");
		item.setPriceM(5000);
		item.setPriceL(2600);
		item.setDescription("えっびまよまよ");
		item.setDeleted(false);
		item.setPopularity(null);
		service.save(item);

		Item item2 = new Item();
		item2.setId(20);
		item2.setName("おこのみやき");
		item2.setImagePath("画像");
		item2.setPriceM(5000);
		item2.setPriceL(2600);
		item2.setDescription("えっびまよまよ");
		item2.setDeleted(false);
		item2.setPopularity(null);
		service.save(item2);
		List<Item> insertList = service.findAll();
		assertThat("追加されていません", ItemList.size() != insertList.size(), is(true));
	}

	@Test
	public void 商品情報を変更する() {
		List<Item> ItemList = service.findAll();
		Item item = new Item();
		item.setId(20);
		item.setName("たこやき");
		item.setImagePath("がぞう");
		item.setPriceM(50);
		item.setPriceL(26);
		item.setDescription("せつめい");
		item.setDeleted(false);
		item.setPopularity(null);
		service.update(item);
		List<Item> insertList = service.findAll();
		assertThat("変更されていません", ItemList.get(19).getPriceM() != insertList.get(19).getPriceM(), is(true));
	}

	@Test
	public void 商品を削除する() {
		List<Item> ItemList = service.findAll();
		service.deleteAll(20);
		List<Item> deleteList = service.findAll();
		assertThat("変更されていません", ItemList.size() != deleteList.size(), is(true));

	}

}
