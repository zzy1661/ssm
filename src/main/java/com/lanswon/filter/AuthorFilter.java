package com.lanswon.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorFilter implements Filter{
	FilterConfig filterConfig = null;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	        FilterChain chain)
			throws IOException, ServletException {
		
		 HttpServletResponse res = (HttpServletResponse) response;
		 HttpServletRequest req = (HttpServletRequest)request;
		 System.out.println("==================进入filter,sessionId："+req.getSession().getId());
		 res.setHeader("Access-Control-Allow-Credentials","true");
		 res.setHeader("XDomainRequestAllowed","1");
		 res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
		 /*res.setContentType("textml;charset=UTF-8");
	        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	        res.setHeader("Access-Control-Max-Age", "0");
	        res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
	        res.setHeader("XDomainRequestAllowed","1");
	        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
	        res.setHeader("Access-Control-Allow-Credentials", "true");
	        res.setHeader("Access-Control-Allow-Credential", "true");
	        res.setHeader("P3P", "CP=CAO PSA OUR");
	        if(req.getHeader("Access-Control-Request-Method")!=null&&"OPTIONS".equals(req.getMethod())) {
	            res.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE,OPTIONS");
	            res.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
	            res.addHeader("Access-Control-Max-age", "120");
	        }*/
		 chain.doFilter(request, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 this.filterConfig = filterConfig;
		
	}

}
