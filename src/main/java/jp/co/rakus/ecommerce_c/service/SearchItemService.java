package jp.co.rakus.ecommerce_c.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.repository.ItemRepository;

/**
 * 曖昧検索を実行するサービスクラス.
 * 
 * @author ryo.kamiyama
 *
 */

@Service
@Transactional
public class SearchItemService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 入力情報を元に曖昧検索をする.
	 * @param keyword 入力ワード
	 * @return searchItemList 検索した商品データ
	 */
	public List<Item> searchItem(String keyword) {
		return itemRepository.searchItem(keyword);
	}

}
