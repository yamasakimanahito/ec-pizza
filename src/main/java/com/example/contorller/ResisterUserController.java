package com.example.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.ResisterUserForm;
import com.example.service.ResisterUserService;



/**
 * ユーザー情報を操作するコントローラー.
 * 
 * @author nanakono
 *
 */
@Controller
@RequestMapping("/")
public class ResisterUserController {
	
	@Autowired
	private ResisterUserService resisterUserService;
	
	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @param form フォーム
	 * @return　ユーザー登録画面
	 */
	@GetMapping("/index")
	public String index(ResisterUserForm form) {
		return "register_user";
	};
	
	/**
	 * ユーザー情報を登録します.
	 * 
	 * @param form ユーザー情報用フォーム
	 * @param result
	 * @param model モデル
	 * @return ログイン画面
	 */
	@PostMapping("/resisterUser")
	
	public String resisterUser(ResisterUserForm form,  Model model) {
		//* パスワード確認 *//
//		if(!form.getConfirmPassword().equals(form.getPassword())) {
//			result.rejectValue("confirmPassword", "", "パスワードと確認用パスワードが不一致です");
//		}
		
		//*　メールアドレス重複確認 *//
		
		
//		if(result.hasErrors()) {
//			return "index";
//		}
		
		
		//* ユーザー情報登録 *//
		User user = new User();
		user.setName(form.getName());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		user.setZipcode(form.getZipcode());
		user.setAddress(form.getAddress());
		user.setTelephone(form.getTelephone());
		model.addAttribute("user", user);
		resisterUserService.resisterUser(user);
		return "/login";
	}
	

}
