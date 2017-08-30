package jp.co.rakus.ecommerce_c.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private HttpSession session;
	
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
		if(session.getAttribute("randomSessionId") == null){
			session.setAttribute("randomSessionId", new Random().nextInt(1147483640)+1000000000);
		}
		return "registerUser";
	}
	
	/**
	 * 渡された情報でユーザ登録処理を行う.
	 * 
	 * @param form 入力情報
	 * @param result エラーチェック
	 * @param model リクエストパラメータ
	 * @return 登録成功すればログイン画面、エラー時は登録画面
	 */
	@RequestMapping("/submit")
	private String submit(@Validated UserForm form, BindingResult result, Model model){
		// 入力値チェック
		if(!form.getPassword().isEmpty()){
			// パスワードが8文字以上か
			if(form.getPassword().length() >= 8){
				if(!form.getPassword().equals(form.getReInputPassword())){
					result.rejectValue("password", null, "確認欄と異なるパスワードが入力されました");
				}
			}
		}
		if(regiseterUserService.findByEmail(form.getEmail()) != null){
			result.rejectValue("email", null, "そのメールアドレスはすでに使われています");
		}
		if(result.hasErrors()){
			return registerUser(model);
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		regiseterUserService.registUser(user);
		return "redirect:/login";
	}

}
