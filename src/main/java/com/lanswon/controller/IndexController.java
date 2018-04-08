package com.lanswon.controller;

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
@RequestMapping("/index")
public class IndexController {
	/**
	 * 首页某数据请求
	 * @param request
	 * @return
	 */
	@RequestMapping(method={RequestMethod.GET})
	@ResponseBody
	public Object index(HttpServletRequest request) {
		
		return "成功调用api";
	}
	
}
