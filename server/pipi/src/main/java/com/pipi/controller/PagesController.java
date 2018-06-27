package com.pipi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pipi.entity.Result;

@Controller
@PropertySource(value = "classpath:user.properties")
public class PagesController {

	@Value("${ADMINNAME}")
	private String ADMINNAME;

	@Value("${PASSWORD}")
	private String PASSWORD;

	@RequestMapping("/")
	public String login() {
		return "login";
	}

	@RequestMapping("/toLogin")
	@ResponseBody
	public Result toLogin(String username, String password, HttpSession session) {
		if (!StringUtils.isNotBlank(username) || !StringUtils.isNotBlank(password)) {
			return Result.error("用户名或者密码不能为空！");
		} else if (ADMINNAME.equals(username) && PASSWORD.equals(password)) {
			session.setAttribute("username", username);
			return Result.ok();
		} else {
			return Result.error("用户名或密码错误！");
		}
	}

	@RequestMapping("/loginout")
	@ResponseBody
	public void loginOut(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/toIndex")
	public String index(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		return "index";
	}

	@RequestMapping("pages/{pageName}")
	public String toPage(@PathVariable("pageName") String pageName) {
		return pageName;
	}
}
