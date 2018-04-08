package com.lanswon.entity;

import java.io.Serializable;

public class ResultMsg implements Serializable {
	private static final long serialVersionUID = -5230789528043956195L;

	private int status;
	private String msg;
	private Object data;

	public ResultMsg() {
	}

	public ResultMsg(int status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public ResultMsg(int status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
