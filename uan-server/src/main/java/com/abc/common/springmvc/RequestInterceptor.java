package com.abc.common.springmvc;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.abc.common.UanException;
import com.abc.common.bus.RequestBus;
import com.abc.common.bus.ThreadContext;
import com.abc.common.util.BeanFactoryUtils;
import com.abc.common.util.CfgValueHandler;
import com.abc.common.util.JsonConvertor;
import com.abc.common.util.LogWriter;
import com.abc.common.util.UUIDNumberGenerator;

/**
 * http控制器拦截器。
 * 
 * @author liubo
 * 
 */
public class RequestInterceptor implements HandlerInterceptor {

	private static final String SEGMENT_PRE = "pre";
	private static final String SEGMENT_AFTER = "after";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LogWriter.info(RequestInterceptor.class,
				"Request:  URL = " + request.getLocalAddr() + ":" + request.getLocalPort() + request.getRequestURI()
						+ " , ParameterMap = "
						+ BeanFactoryUtils.getBean(JsonConvertor.class).toJson(request.getParameterMap()));
		String uri = request.getRequestURI().substring(request.getContextPath().length());

		try {
			if (StringUtils.isEmpty(uri) || uri.startsWith("/api/rest") || uri.startsWith("/jolokia") || "/".equals(uri)
					|| checkUrlExclusion(uri)) {

				Object o = ThreadContext.getContext().getAttribute(RequestBus.REQUEST_BUS);
				if (o != null) {
					if (!(o instanceof RequestBus)) {
						LogWriter.info(RequestInterceptor.class, "存在未清理的请求上下文对象，请注意！RequestBus：[{}]", o);
						ThreadContext.getContext().setAttribute(RequestBus.REQUEST_BUS,
								new RequestBus(request.getRequestURI()));
					}
				} else {
					ThreadContext.getContext().setAttribute(RequestBus.REQUEST_BUS,
							new RequestBus(request.getRequestURI()));
				}
				printData(SEGMENT_PRE);

				return true;
			} else {
				rejectHandle(request, response);
				RequestBus requestBus = ((RequestBus) ThreadContext.getContext().getAttribute(RequestBus.REQUEST_BUS));
				if (requestBus != null) {
					requestBus.close(false, true);
					printData(SEGMENT_AFTER);
				}
				return false;
			}
		} catch (Exception e) {
			RequestBus requestBus = ((RequestBus) ThreadContext.getContext().getAttribute(RequestBus.REQUEST_BUS));
			if (requestBus != null) {
				requestBus.close(true, false);
				printData(SEGMENT_AFTER);
			}
			throw e;
		}
	}

	private boolean checkUrlExclusion(String uri) {
		String urlExclusions = BeanFactoryUtils.getBean(CfgValueHandler.class).getUrlExclusions();
		if (StringUtils.isNotEmpty(urlExclusions)) {
			String[] urls = urlExclusions.split(",");
			for (String u : urls) {
				if (StringUtils.isEmpty(u)) {
					continue;
				}
				if (uri.startsWith(u)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LogWriter.info(RequestInterceptor.class,
				"结束。" + "Request:  URL = " + request.getLocalAddr() + ":" + request.getLocalPort()
						+ request.getRequestURI() + " , ParameterMap = "
						+ BeanFactoryUtils.getBean(JsonConvertor.class).toJson(request.getParameterMap()));
		boolean error = false;
		if (ex != null) {
			LogWriter.error(RequestInterceptor.class, "访问请求错误。", ex);
			error = true;
		}
		RequestBus bus = ((RequestBus) ThreadContext.getContext().getAttribute(RequestBus.REQUEST_BUS));
		if (bus != null) {
			bus.close(error, false);
			printData(SEGMENT_AFTER);

		}

		if (error) {
			throw new UanException("请求处理异常。", ex);
		}
	}

	private void printData(String segment) {
		RequestBus bus = ((RequestBus) ThreadContext.getContext().getAttribute(RequestBus.REQUEST_BUS));
		if (bus == null) {
			LogWriter.info(RequestInterceptor.class, "缺少RequestBus");
			return;
		}
		if (SEGMENT_PRE.equals(segment)) {
			LogWriter.info(RequestInterceptor.class, "交易开始:[{}]", bus);
		} else if (SEGMENT_AFTER.equals(segment)) {
			LogWriter.info(RequestInterceptor.class, "交易结束:[{}]", bus);
		}

	}

	private void rejectHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String loginUrl = BeanFactoryUtils.getBean(CfgValueHandler.class).getLoginUrl();

		if (request.getHeader("X-Requested-With") != null) {
			response.setContentType("application/json;charset=UTF-8");
			com.abc.common.Response message = new com.abc.common.Response();
			message.createError("用户未登录或已超时，请登录", RequestInterceptor.class);
			response.getWriter().print(message.toString());
			return;
		}
		request.setAttribute("response", HttpServletResponse.SC_UNAUTHORIZED + ":用户未登录或已超时，请登录。");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		StringBuilder builder = new StringBuilder();
		builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
		String rootPath = "";
		if (StringUtils.isEmpty(loginUrl)) {
			rootPath = request.getContextPath() + "/?th_="
					+ BeanFactoryUtils.getBean(UUIDNumberGenerator.class).generate();
		} else {
			rootPath = request.getContextPath() + loginUrl + "?th_="
					+ BeanFactoryUtils.getBean(UUIDNumberGenerator.class).generate();
		}

		builder.append("top.location.href=\"").append(rootPath).append("\";");
		builder.append("</script>");
		out.print(builder.toString());
		out.close();
	}

}
