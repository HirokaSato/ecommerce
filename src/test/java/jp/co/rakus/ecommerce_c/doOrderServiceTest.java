package jp.co.rakus.ecommerce_c;

import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jp.co.rakus.ecommerce_c.domain.Order;
import jp.co.rakus.ecommerce_c.service.doOrderService;

public class doOrderServiceTest {
	@Autowired
	private doOrderService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Order order = new Order();
		order.setId(0000);
		order.setUserId(0000);
		order.setStatus(0);
		order.setTotalPrice(10000);
		order.setOrderDate(new SimpleDateFormat("yyyy-MM-dd").parse("1992-12-19"));
		order.setDestinationName("テスト");
		order.setDestinationEmail("test@docomo.ne.jp");
		order.setDestinationZipcode("000-0000");
		order.setDestinationAddress("とうきょう");
		order.setDestinationTel("00000000");
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		order.setDeliveryTime(timestamp);
		order.setPaymentMethod(1000);
		
		
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

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
