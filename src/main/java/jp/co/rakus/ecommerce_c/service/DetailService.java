package jp.co.rakus.ecommerce_c.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.domain.Topping;
import jp.co.rakus.ecommerce_c.repository.ItemRepository;
import jp.co.rakus.ecommerce_c.repository.ToppingRepository;

/**
 * 詳細画面を表示するサービスクラス.
 * 
 * @author ryo.kamiyama
 *
 */

@Service
@Transactional
public class DetailService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;

	/**
	 * トッピング情報を全件表示する
	 * @return toppingList toppingsテーブル全てのデータ
	 */
	public List<Topping> findAllTopping() {
		return toppingRepository.findAllTopping();
	}
	
	/**
	 * 商品IDを元に商品情報を検索する.
	 * @param id 商品ID
	 * @return item 検索した商品データ
	 */
	public Item loadItem(long id) {
		return itemRepository.loadItem(id);
	}

}
