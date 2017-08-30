package jp.co.rakus.ecommerce_c.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.repository.ItemRepository;

/**
 * Top画面を表示するサービスクラス.
 * 
 * @author ryo.kamiyama
 *
 */

@Service
@Transactional
public class TopService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品内容を全件表示
	 * @return 取得した全てのデータ
	 */
	public List<Item> findAllItem() {
		return itemRepository.findAllItem();

	}
}
