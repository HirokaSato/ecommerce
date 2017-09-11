package jp.co.rakus.ecommerce_c.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.LoginUser;
import jp.co.rakus.ecommerce_c.domain.User;

/**
 * @author hiroka.sato
 *
 */
@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	
	@RequestMapping("/")
	public String view(@AuthenticationPrincipal LoginUser loginUser){
		User user=new User();
		user.setName(loginUser.getUsername());
		return "myPage";
	}
	

}
