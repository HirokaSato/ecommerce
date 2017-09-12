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

import jp.co.rakus.ecommerce_c.domain.LoginUser;
import jp.co.rakus.ecommerce_c.domain.User;
import jp.co.rakus.ecommerce_c.form.UserForm;
import jp.co.rakus.ecommerce_c.service.RegisterUserService;

/**
 * @author hiroka.sato
 *
 */
@Controller
@RequestMapping("/mypage")
public class MyPageController {
	@Autowired
	private RegisterUserService regiseterUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private HttpSession session;

	@ModelAttribute
	public UserForm setUserForm() {
		return new UserForm();
	}

	@RequestMapping
	public String myPageView(@AuthenticationPrincipal LoginUser loginUser) {
		return "myPage";
	}

	/**
	 * 渡された情報でユーザ情報変更処理を行う.
	 * 
	 * @param form
	 *            入力情報
	 * @param result
	 *            エラーチェック
	 * @param model
	 *            リクエストパラメータ
	 * @return 登録成功すればログイン画面、エラー時は登録画面
	 *//*
		 * @RequestMapping("/user_info_change") private String submit(@Validated
		 * UserForm form, BindingResult result, Model model){ // 入力値チェック
		 * if(!form.getPassword().isEmpty()){ // パスワードが8文字以上か
		 * if(form.getPassword().length() < 8){ result.rejectValue("password",
		 * null, "パスワードは8文字以上に設定してください"); }else{
		 * if(!form.getPassword().equals(form.getReInputPassword())){
		 * result.rejectValue("password", null, "確認欄と異なるパスワードが入力されました"); } } }
		 * try{ if(!form.getTelephone().isEmpty()){
		 * Long.parseLong(form.getTelephone()); } }catch(NumberFormatException
		 * e){ result.rejectValue("telephone", null, "数字で入力してください"); }
		 * 
		 * if(result.hasErrors()){ return "myPage"; }
		 * 
		 * User user = new User(); BeanUtils.copyProperties(form, user);
		 * user.setPassword(passwordEncoder.encode(form.getPassword()));
		 * regiseterUserService.registUser(user); return "redirect:/mypage"; }
		 * 
		 */

}
