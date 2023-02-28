package com.example.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.LoginLogoutUserForm;
import com.example.service.LoginLogoutUserService;

import jakarta.servlet.http.HttpSession;

/**
 * 顧客のログインを操作するコントローラー.
 * 
 * @author yoshimatsushouta
 *
 */
@Controller
@RequestMapping("/LoginLogoutUser")
public class LoginLogoutUserController {

	@Autowired
	private LoginLogoutUserService loginLogoutUserService;

	@Autowired
	private HttpSession session;

	/**
	 * ログイン画面に遷移.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/toLogin")
	public String toLogin() {
		return "/materialize-version/login.html";
	}

	/**
	 * フォームで受け取った情報でログインを行う.
	 * 
	 * @param form  フォーム
	 * @param model ログインできなかった場合エラーメッセージと共にログイン画面に遷移
	 * @return Userをセッションスコープに保存し、ShowItemControllerに遷移
	 */
	@PostMapping("/login")
	public String login(LoginLogoutUserForm form, Model model) {
		User user = loginLogoutUserService.login(form);
		if (user == null) {

			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
			return toLogin();
		}
		session.setAttribute("User", user); // ログイン状態を保存する
		return "redirect:/ShowItemList/showItem";
	}

	/**
	 * ログアウトを行う.
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/LoginLogoutUser/toLogin";
	}

}
