package com.lanswon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lanswon.entity.DownloadRecord;
import com.lanswon.entity.ResultMsg;
@CrossOrigin(origins = "*") 
@Controller
@RequestMapping("/file")
public class FileController {
	@RequestMapping("/springdown")
	public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {
	    File file = new File("C:/Users/User/Pictures/RR.png");
	    byte[] body = null;
	    InputStream is = new FileInputStream(file);
	    body = new byte[is.available()];
	    is.read(body);
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "attchement;filename=" + file.getName());
	    HttpStatus statusCode = HttpStatus.OK;
	    ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
	    return entity;
	}
	@RequestMapping("/javadown")
	public static void download(String fileName, String filePath,
			HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
	    //声明本次下载状态的记录对象
	    DownloadRecord downloadRecord = new DownloadRecord(fileName, filePath, request);
	    //设置响应头和客户端保存文件名
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("multipart/form-data");
	    response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
	    //用于记录以完成的下载的数据量，单位是byte
	    long downloadedLength = 0l;
	    try {
	        //打开本地文件流
	        InputStream inputStream = new FileInputStream(filePath);
	        //激活下载操作
	        OutputStream os = response.getOutputStream();

	        //循环写入输出流
	        byte[] b = new byte[2048];
	        int length;
	        while ((length = inputStream.read(b)) > 0) {
	            os.write(b, 0, length);
	            downloadedLength += b.length;
	        }

	        // 这里主要关闭。
	        os.close();
	        inputStream.close();
	    } catch (Exception e){
	        downloadRecord.setStatus(DownloadRecord.STATUS_ERROR);
	        throw e;
	    }
	    downloadRecord.setStatus(DownloadRecord.STATUS_SUCCESS);
	    downloadRecord.setEndTime(new Timestamp(System.currentTimeMillis()));
	    downloadRecord.setLength(downloadedLength);
	    //存储记录
	}
	/**
	 * 单文件上传
	 * @param file
	 * @param msg
	 * @return
	 */
	@RequestMapping(value = "/singleup", method={RequestMethod.POST})
	public Object singleup(@RequestParam MultipartFile file, @RequestParam(required=false) String msg) {
		System.out.println("single:"+file.getName());
		return ResponseEntity.status(HttpStatus.OK).body("test OK");
	}
	/**
	 * 多文件上传
	 * @param files
	 * @param msg
	 * @return
	 */
	@RequestMapping(value = "/multiup", method={RequestMethod.POST})
	public Object multiup(@RequestParam("file") List<MultipartFile> files, @RequestParam(required=false) String msg) {
		System.out.println("mutil:"+files.size());
		return ResponseEntity.status(HttpStatus.OK).body("test OK");
	}
	/**
	 * form文件上传
	 * @param file
	 * @param msg
	 * @return
	 */
	@RequestMapping(value = "/formup", method={RequestMethod.POST})
	@ResponseBody
	public Object formup(@RequestParam MultipartFile file, @RequestParam(required=false) String msg, HttpServletResponse resp) {
		System.out.println("form:"+file.getName());
		resp.setContentType("text/html;charset=UTF-8");
		return new ResultMsg(1,"ok");
	}

}
