package jp.co.rakus.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManageController {
	@RequestMapping
	public String manageView() {
		return "manage";
	}
}
