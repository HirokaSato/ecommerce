package jp.co.rakus.ecommerce_c.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.rakus.ecommerce_c.domain.Topping;

/**
 * Topping関連のサービスクラス.
 * 
 * @author ryo.kamiyama
 *
 */

@Service
@Transactional
public class ToppingService {

	@Autowired
	private ToppingService toppingService;

	public List<Topping> findAllTopping() {
		return toppingService.findAllTopping();
	}

}
