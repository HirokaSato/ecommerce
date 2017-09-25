package jp.co.rakus.ecommerce_c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.repository.ItemRepository;

@Service
public class ProductManagementService {
	@Autowired
	private ItemRepository repository;
	
	public List<Item> findAll(){
		return repository.findAllItem();
	}
	
	public Item save(Item item){
		return repository.save(item);
	}
	
	public void deleteAll(){
		repository.deleteAll();
	}
	
}
