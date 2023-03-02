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

//	@Autowired
//	private LoginLogoutUserService loginLogoutUserService;

//	@Autowired
//	private HttpSession session;

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

//	/**
//	 * フォームで受け取った情報でログインを行う.
//	 * 
//	 * @param form  フォーム
//	 * @param model ログインできなかった場合エラーメッセージと共にログイン画面に遷移
//	 * @return Userをセッションスコープに保存し、ShowItemControllerに遷移
//	 */
//	@PostMapping("/login")
//	public String login(LoginLogoutUserForm form, Model model) {
//		UserInfo user = loginLogoutUserService.login(form);
//		if (user == null) {
//
//			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
//			return toLogin();
//		}
//		session.setAttribute("User", user); // ログイン状態を保存する
//		return "redirect:/ShowItemList/showItem";
//	}

//	/**
//	 * ログアウトを行う.
//	 * 
//	 * @return ログイン画面
//	 */
//	@GetMapping("/logout")
//	public String logout() {
//		session.invalidate();
//		return "redirect:/LoginLogoutUser/toLogin";
//	}

}
