package jp.co.rakus.ecommerce_c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.form.UserForm;

@Controller
@RequestMapping("/login")
public class loginController {
	@ModelAttribute
	public UserForm setUserForm(){
		return new UserForm();
	}

	@RequestMapping
	public String login(String error, Model model){
		if("true".equals(error)){	
			model.addAttribute("error", "メールアドレス、またはパスワードが間違っています");
		}
		return "login";
	}
}
