package jp.co.rakus.ecommerce_c.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
//import static org.junit.Assert.fail;

//import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;

//import jp.co.rakus.ecommerce_c.domain.Item;
import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.domain.OrderItem;
import jp.co.rakus.ecommerce_c.domain.OrderTopping;
//import jp.co.rakus.ecommerce_c.domain.User;
//import jp.co.rakus.ecommerce_c.repository.OrderItemRepository;
//import jp.co.rakus.ecommerce_c.repository.OrderRepository;
//import jp.co.rakus.ecommerce_c.repository.OrderToppingRepository;

public class AddItemToCartServiceTest {

//	@Autowired
//	private AddItemToCartService addItemToCartService;
//	@Autowired
//	private OrderRepository orderRepository;
//	@Autowired
//	private OrderItemRepository orderItemRepository;
//	@Autowired
//	private OrderToppingRepository orderToppingRepository;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	//未完成
	@Test
	public void カートの中に商品が入っているかどうか() {

		List<Order> orderList = new ArrayList<>();
		List<OrderItem> orderItem =new ArrayList<>();
		List<OrderTopping> orderToppingList = new ArrayList<>();
	
		orderList.add(new Order(1, 1, 0, 3000));
		orderItem.add(new OrderItem(1,2,2,"M"));
		orderToppingList.add(new OrderTopping( ));
				
		assertThat("TC:1期待値と結果が異なります", orderList, is(true));
	
	
	}
}	
