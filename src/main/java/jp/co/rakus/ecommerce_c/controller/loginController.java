package jp.co.rakus.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class loginController {

	@RequestMapping
	public String login(String error, Model model){
		if(Boolean.getBoolean(error) == true){			
			model.addAttribute("error", "メールアドレス、またはパスワードが間違っています");
		}
		return "login";
	}
}
