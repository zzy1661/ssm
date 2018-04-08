package com.lanswon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanswon.entity.ErrorResult;
/**
 * 测试各类型http status
 * @author User
 *
 */
@CrossOrigin(origins = "*") 
@Controller
@RequestMapping("/http")
public class HttpStatusTest {
	
	@RequestMapping(value="/400")
	public ResponseEntity<?> status400(){
		ErrorResult errorResult = new ErrorResult();
		errorResult.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResult.setMessage("发生异常：error bad request");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
	}
	@RequestMapping(value="/403")
	@ResponseBody
	public ResponseEntity<?> status403(){
		ErrorResult errorResult = new ErrorResult();
		errorResult.setStatus(HttpStatus.FORBIDDEN.value());
		errorResult.setMessage("发生异常：error FORBIDDEN");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResult);
	}
	@RequestMapping(value="/500")
	@ResponseBody
	public ResponseEntity<?> status500(){
		ErrorResult errorResult = new ErrorResult();
		errorResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResult.setMessage("发生异常：error INTERNAL_SERVER_ERROR");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
	}
	@RequestMapping(value="/ok")
	public ResponseEntity<?> statusTest(){
		return ResponseEntity.ok("ok");
	}

}
