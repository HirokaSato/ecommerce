package jp.co.rakus.ecommerce_c.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.repository.ItemRepository;

/**
 * 商品関連のサービスクラス.
 * 
 * @author ryo.kamiyama
 *
 */

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public List<Item> findAll() {
		return itemRepository.findAll();

	}

}
