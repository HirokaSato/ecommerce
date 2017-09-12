package jp.co.rakus.ecommerce_c.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ecommerce_c.domain.LoginUser;
import jp.co.rakus.ecommerce_c.domain.User;
import jp.co.rakus.ecommerce_c.form.UserForm;
import jp.co.rakus.ecommerce_c.service.RegisterUserService;

/**
 * 注文履歴を表示するクラス
 * 
 * @author hiroka.sato
 *
 */
@Controller
@RequestMapping
public class UserInfoChangeController {
	@Autowired
	private RegisterUserService service;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public UserForm setUserForm(){
		return new UserForm();
	}
	/**
	 * アカウント情報変更ページを表示する
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/user_info_change")
	public String userInfoChange() {

		return "UserInfoChange";

	}

	/**
	 * 渡された情報でユーザ情報変更処理を行う.
	 * 
	 * @param form 入力情報
	 * @param result エラーチェック
	 * @param model リクエストパラメータ
	 * @return 登録成功すればログイン画面、エラー時は登録画面
	 */
	@RequestMapping("/changed_userInfo")
	private String submit(@Validated UserForm form, BindingResult result,RedirectAttributes attributes,
			@AuthenticationPrincipal LoginUser loginUser){
		// 入力値チェック
		if(!form.getPassword().isEmpty()){
			// パスワードが8文字以上か
			if(form.getPassword().length() < 8){
				result.rejectValue("password", null, "パスワードは8文字以上に設定してください");
			}else{
				if(!form.getPassword().equals(form.getReInputPassword())){
					result.rejectValue("password", null, "確認欄と異なるパスワードが入力されました");
				}
			}
		}
		try{
			if(!form.getTelephone().isEmpty()){
				Long.parseLong(form.getTelephone());
			}
		}catch(NumberFormatException e){
			result.rejectValue("telephone", null, "数字で入力してください");
		}
		
		if(result.hasErrors()){
			return userInfoChange();
		}
		
		User user = loginUser.getUser();//参照渡ししている
		BeanUtils.copyProperties(form, user);
		//user.setId(form.getLongId());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		service.updateUser(user);
		return "redirect:/mypage";
	}
}
