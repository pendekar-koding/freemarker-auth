package com.pendekar.koding.freemarkerauth.common.response;

import com.pendekar.koding.freemarkerauth.common.controller.BaseController;

public class CommonResponses<T> extends BaseController {

	public CustomReturn<T> commonSuccessResponse(T wrapper) {
		CustomReturn<T> customReturn = new CustomReturn<>();
		customReturn.setMessage(STR_SUCCESS);
		customReturn.setStatus(true);
		customReturn.setStatusCode("200");
		customReturn.setDatas(wrapper);
		return customReturn;
	}

	public CustomReturn<T> commonFailedResponse() {
		CustomReturn<T> customReturn = new CustomReturn<>();
		customReturn.setMessage(STR_FAILED);
		customReturn.setStatus(false);
		customReturn.setStatusCode("500");
		return customReturn;
	}

	public CustomReturn<T> commonFailedError() {
		CustomReturn<T> customReturn = new CustomReturn<>();
		customReturn.setMessage(STR_ERROR);
		customReturn.setStatus(false);
		customReturn.setStatusCode("500");
		return customReturn;
	}

	public CustomReturn<T> commonDeleteSuccess(){
		CustomReturn<T> customReturn = new CustomReturn<>();
		customReturn.setMessage(STR_SUCCESS);
		customReturn.setStatus(true);
		customReturn.setStatusCode("200");
		return customReturn;
	}

	public CustomReturn<T> commonLoginSuccessResponse(T wrapper) {
		CustomReturn<T> customReturn = new CustomReturn<>();
		customReturn.setMessage(STR_SUCCESS);
		customReturn.setStatus(true);
		customReturn.setStatusCode("200");
		customReturn.setDatas(wrapper);
		return customReturn;
	}

	public CustomReturn<T> commonLoginFailed() {
		CustomReturn<T> customReturn = new CustomReturn<>();
		customReturn.setMessage("Unauthorized");
		customReturn.setStatus(false);
		customReturn.setStatusCode("401");
		return customReturn;
	}

	public CustomReturn<T> commonLoginFailedError() {
		CustomReturn<T> customReturn = new CustomReturn<>();
		customReturn.setMessage("Forbidden");
		customReturn.setStatus(false);
		customReturn.setStatusCode("403");
		return customReturn;
	}

}
