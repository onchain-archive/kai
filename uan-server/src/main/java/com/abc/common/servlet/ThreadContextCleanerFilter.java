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
package com.abc.common.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.abc.common.bus.ThreadContext;
import com.abc.common.util.LogWriter;

/**
 * 清理线程上下文程序
 * 
 * @author liubo
 */
public class ThreadContextCleanerFilter implements Filter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		LogWriter.trace(ThreadContextCleanerFilter.class, "为["
				+ ((HttpServletRequest) request).getRequestURI() + "]启动线程上下文。");
		try {
			chain.doFilter(request, response);
		} finally {
			ThreadContext.getContext().clear();
			LogWriter.trace(ThreadContextCleanerFilter.class, "清理线程上下文。");
			LogWriter.trace(ThreadContextCleanerFilter.class, "结束过滤器，线程上下文情况："
					+ ThreadContext.print());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override public void init(FilterConfig arg0) throws ServletException {
	}
}
