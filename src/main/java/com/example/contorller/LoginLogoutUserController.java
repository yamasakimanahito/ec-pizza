package com.example.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 顧客のログインを操作するコントローラー.
 * 
 * @author yoshimatsushouta
 *
 */
@Controller
@RequestMapping("/")
public class LoginLogoutUserController {

	/**
	 * ログイン画面に遷移.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/toLogin")
	public String toLogin(Model model, @RequestParam(required = false) String error) {
		System.err.println("login error:" + error);
		if (error != null) {
			System.err.println("login failed");
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
		}
		return "/materialize-version/login.html";

	}

}
