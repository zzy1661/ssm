package com.lanswon.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
//@CrossOrigin(origins = "*") 
@Controller
@RequestMapping("/toy")
public class ToyTests {
	@RequestMapping(value = "/test", method={RequestMethod.GET})
	@ResponseBody
	public Object getNoShapeDevice(HttpServletRequest request) {
		String id = request.getSession().getId();
		System.out.println("sessionId "+id);
		return id;
	}
	
	/**
	 * 文件上传test
	 * @param file
	 * @param msg
	 * @return
	 */
	@RequestMapping(value = "/fileup", method={RequestMethod.POST})
	@ResponseBody
	public Object getNoShapeDevice(@RequestParam MultipartFile file, @RequestParam String msg) {
		System.out.println(file.getName());
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error test");
		return ResponseEntity.status(HttpStatus.OK).body("test OK");
	}
	/**
	 * 获取公网ip
	 * @return
	 */
	@RequestMapping(value = "/ip1", method={RequestMethod.GET})
	@ResponseBody
	public Object getip(HttpServletRequest request) {
		
		String ipAddress = request.getHeader("x-forwarded-for");  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getRemoteAddr();  
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                //根据网卡取本机配置的IP  
                InetAddress inet=null;  
                try {  
                    inet = InetAddress.getLocalHost();  
                } catch (UnknownHostException e) {  
                    e.printStackTrace();  
                }  
                ipAddress= inet.getHostAddress();  
            }  
        }  
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
            if(ipAddress.indexOf(",")>0){  
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
            }  
        }  
        return ipAddress; 
	}
	
}
