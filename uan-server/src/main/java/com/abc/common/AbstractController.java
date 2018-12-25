/*
 * Copyright 2018 Liu Bo
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abc.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abc.common.bus.RequestBus;
import com.abc.common.bus.ThreadContext;
import com.abc.common.util.CfgValueHandler;
import com.abc.common.util.JsonConvertor;
import com.abc.common.util.LogWriter;
import com.abc.common.util.UUIDNumberGenerator;

/**
 * Title: AbstractController
 * @Description: AbstractController
 * @author Bo Liu
 * @date 2018-09-20
 */
public abstract class AbstractController {

	/**
	 * 自动建立的字段: RESPONSE_ERROR_CODE
	 */
	private static final int RESPONSE_ERROR_CODE = 404;

	public static final String REST_API_PREFIX = "/api/rest";
	public static final String VIEW_PREFIX = "/view";

	@Autowired
	protected JsonConvertor jsonConvertor;

	@Autowired
	protected CfgValueHandler cfgValueHandler;

	@Autowired
	private UUIDNumberGenerator uuidNumberGenerator;

	/**
	 * 异常处理
	 * 
	 * @param e
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Throwable.class)
	public void handleException(Throwable e, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		long begin = System.currentTimeMillis();
		Response error = new Response();
		Throwable tmpThrowable = e;
		// 根据拒绝访问异常，修改为404
		if (e instanceof RejectedAccessException) {
			response.sendError(RESPONSE_ERROR_CODE);
		}
		if (!(e instanceof UanException) && !(e instanceof BindException)
				&& !(e instanceof MethodArgumentNotValidException)) {
			tmpThrowable = new UanException("系统运行错误,请稍后重试!" + e.getMessage(), e);
			error.createError(tmpThrowable);
		} else {
			if (e instanceof BindException || e instanceof MethodArgumentNotValidException) {

				BindingResult br = (e instanceof BindException) ? ((BindException) e).getBindingResult()
						: ((MethodArgumentNotValidException) e).getBindingResult();
				String msg = "输入参数校验错误，错误数为" + br.getErrorCount() + " 个:";
				StringBuilder detail = new StringBuilder("");

				if (br.getFieldErrorCount() > 0) {
					List<FieldError> fieldErrorList = br.getFieldErrors();
					for (FieldError err : fieldErrorList) {
						String str = err.getObjectName() + "对象" + err.getField() + "属性：" + err.getDefaultMessage()
								+ "&lt;br&gt;";
						detail.append(str);
					}
				}
				if (br.getGlobalErrorCount() > 0) {
					List<ObjectError> globalErrorList = br.getGlobalErrors();
					for (ObjectError err : globalErrorList) {
						String str = err.getObjectName() + "对象：" + err.getDefaultMessage() + "&lt;br&gt;";
						detail.append(str);
					}
				}
				error.createError(msg, AbstractController.class);
				error.setDetail(detail.toString());

			} else {
				error.createError(e);
			}
		}

		LogWriter.trace(this.getClass(), "异常信息处理时间：" + (System.currentTimeMillis() - begin));
		// LogWriter.runtime(this.getClass(),
		// "请求服务异常：" + tmpThrowable.getMessage());
		LogWriter.error(this.getClass(), "请求服务异常：" + tmpThrowable.getMessage(), tmpThrowable);
		RequestBus bus = (RequestBus) ThreadContext.getContext().getAttribute(RequestBus.REQUEST_BUS);
		bus.setState("EXCEPTION");

		processErrorResponseHeader(error, request, response);
	}

	private void processErrorResponseHeader(Response error, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RequestBus bus = (RequestBus) ThreadContext.getContext().getAttribute(RequestBus.REQUEST_BUS);
		if (request.getHeader("X-Requested-With") != null) {
			error.setAppId(cfgValueHandler.getAppId());
			error.setUniqueId(bus.getUniqueId());
			String errorJson = jsonConvertor.toJson(error);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(errorJson);
			response.flushBuffer();
			LogWriter.info(this.getClass(), "AJAX请求的ErrorResponse");
			// LogWriter.runtime(this.getClass(), "AJAX请求的ErrorResponse：" + errorJson);
		} else {
			String errorJson = jsonConvertor.toJson(error);
			request.setAttribute("message", errorJson);
			LogWriter.info(this.getClass(), "视图请求的ErrorResponse");
			// LogWriter.runtime(this.getClass(), "视图请求的ErrorResponse：" + errorJson);
			String errorFile = "error.jsp";
			if (StringUtils.isEmpty(errorFile)) {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().print(jsonConvertor.toJson(error.createError(new UanException("未配置项目错误页面"))));
				response.flushBuffer();
				return;
			}
			errorFile = errorFile.trim();
			errorFile = (errorFile.indexOf("/") != 0) ? errorFile : errorFile.substring(1);
			errorFile = errorFile + "?th_=" + uuidNumberGenerator.generate();
			request.getRequestDispatcher("/" + errorFile).forward(request, response);
			response.setContentType("text/html;charset=UTF-8");
		}
	}
}
