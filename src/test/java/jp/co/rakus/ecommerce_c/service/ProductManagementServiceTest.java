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
		assertThat("表示されていません", ItemList.size(), is(18));
		assertThat("価格が昇順ではありません", ItemList.get(2).getPriceM()< ItemList.get(3).getPriceM(), is(true));
		assertThat("価格が昇順ではありません", ItemList.get(5).getPriceM() < ItemList.get(9).getPriceM(), is(true));
	}
	
	@Test
	public void 新商品を追加する(){
		
		
	}

}
