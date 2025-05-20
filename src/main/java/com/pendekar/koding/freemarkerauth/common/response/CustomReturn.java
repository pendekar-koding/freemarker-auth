package com.pendekar.koding.freemarkerauth.common.response;

public class CustomReturn<T> {
	private String message;
	private String exception;
	private Exception detail;
	private Boolean status;
	private String statusCode;
	private T datas;


	public CustomReturn() {
	}

	public CustomReturn(String message, String exception, Exception detail, Boolean status, String statusCode, T datas) {
		this.message = message;
		this.exception = exception;
		this.detail = detail;
		this.status = status;
		this.statusCode = statusCode;
		this.datas = datas;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Exception getDetail() {
		return detail;
	}

	public void setDetail(Exception detail) {
		this.detail = detail;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public T getDatas() {
		return datas;
	}

	public void setDatas(T datas) {
		this.datas = datas;
	}
}
