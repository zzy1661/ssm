package com.lanswon.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 跨域情况下登录控制和api鉴权
 * @author User
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	/**
	 * 登录：
	 * @param request
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(method={RequestMethod.GET})
	@ResponseBody
	public Object login(HttpServletRequest request,String username,String password) {
		Integer userId = getUser(username,password);
		if(userId!=null){
			//登录成功，获取token
			String token = generateToken();
			//将token和用户id放到session
			request.getSession().setAttribute(token, userId);
		}
		return userId == null?false:true;
	}
	/**
	 * 假设用户名test，密码123456，id：1
	 * @param username
	 * @param password
	 * @return
	 */
	private Integer getUser(String username, String password) {
		if("test".equals(username)&&"123456".equals(password)){
			return 1;
		}else {
			return null;
		}
	}
	/**
	 * 比如说，使用uuid做token
	 * @return
	 */
	private String generateToken(){
		return UUID.randomUUID().toString();
	}
	
}
