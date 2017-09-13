package jp.co.rakus.ecommerce_c.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.LoginUser;

/**
 * @author hiroka.sato
 *
 */
@Controller
@RequestMapping("/mypage")
public class MyPageController {

	@RequestMapping
	public String myPageView(@AuthenticationPrincipal LoginUser loginUser) {
		return "myPage";
	}

}
