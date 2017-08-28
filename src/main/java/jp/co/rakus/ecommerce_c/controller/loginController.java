package jp.co.rakus.ecommerce_c.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.form.UserForm;

@Controller
@RequestMapping("/login")
public class loginController {
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public UserForm setUserForm(){
		return new UserForm();
	}

	@RequestMapping
	public String login(String error, Model model){
		if(session.getAttribute("randomSessionId") == null){
			session.setAttribute("randomSessionId", new Random().nextInt(1147483640)+1000000000);
		}
		if("true".equals(error)){	
			model.addAttribute("error", "メールアドレス、またはパスワードが間違っています");
		}
		return "login";
	}
}
