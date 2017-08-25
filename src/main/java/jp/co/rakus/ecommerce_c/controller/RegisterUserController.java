package jp.co.rakus.ecommerce_c.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ecommerce_c.domain.User;
import jp.co.rakus.ecommerce_c.form.UserForm;
import jp.co.rakus.ecommerce_c.service.RegisterUserService;

/**
 * ユーザ登録処理のコントローラ.
 * 
 * @author shun.nakano
 *
 */
@Controller
@RequestMapping("/registerUser")
public class RegisterUserController {
	@Autowired
	private RegisterUserService regiseterUserService;
	
	@ModelAttribute
	public UserForm setUserForm(){
		return new UserForm();
	}
	
	/**
	 * ユーザ登録画面を表示する.
	 * 
	 * @param model リクエストパラメータ
	 * @return ユーザ登録画面
	 */
	@RequestMapping
	public String registerUser(Model model){
		return "registerUser";
	}
	
	/**
	 * 渡された情報でユーザ登録処理を行う.
	 * 
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("/submit")
	private String submit(@Validated UserForm form, BindingResult result, Model model){
		// 入力値チェック
		if(regiseterUserService.findByEmail(form.getEmail()) != null){
			result.rejectValue("email", null, "そのメールアドレスはすでに使われています");
		}
		if(!form.getPassword().equals(form.getReInputPassword())){
			result.rejectValue("password", null, "確認欄と異なるパスワードが入力されました");
		}
		if(result.hasErrors()){			
			return registerUser(model);
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		return "redirect:/login";
	}

}
