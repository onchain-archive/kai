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
package com.abc.uan.union;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.common.AbstractController;
import com.abc.common.Request;
import com.abc.common.Response;

/**
 * Title: UnionRestApiControllor
 * @Description: UnionRestApiControllor
 * @author Bo Liu
 * @date 2018-09-20
 */
@RestController
@RequestMapping(value = AbstractController.REST_API_PREFIX + "/uni")
public class UnionRestApiControllor extends AbstractController {

	@Autowired
	private UnionServiceImpl unionService;

	/** 
	 * @Description: findAll
	 * @param reqJson
	 * @param req
	 * @return String
	 */ 
	@RequestMapping(value = "/findAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String findAll(@RequestBody String reqJson, HttpServletRequest req) {
		Request request = Request.create(reqJson, req, null);
		List<UnionPojo> unionPojos = unionService.findAll();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(unionPojos, request);
	}

	/** 
	 * @Description: findByWhere
	 * @param unionQuery
	 * @param request
	 * @param req
	 * @return resp.createSuccessJson
	 */ 
	@RequestMapping(value = "/findByWhere", method = RequestMethod.GET)
	public String findByWhere(UnionQuery unionQuery, Request<UnionQuery> request,
			HttpServletRequest req) {
		request = Request.create(unionQuery, request, req);
		List<UnionPojo> unionPojos = unionService.findByWhere(request.getData());
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(unionPojos, request);
	}

	/** 
	 * @Description: loadByCode
	 * @param reqJson
	 * @param req
	 * @return resp.createSuccessJson(unionPojo, request)
	 */ 
	@RequestMapping(value = "/loadByCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String loadByCode(@RequestBody String reqJson, HttpServletRequest req) {
		Request<String> request = Request.create(reqJson, req, String.class);
		UnionPojo unionPojo = unionService.loadByCode(request.getData());
		Response<UnionPojo> resp = new Response<UnionPojo>();
		return resp.createSuccessJson(unionPojo, request);
	}

	/** 
	 * @Description: create
	 * @param reqJson
	 * @param req
	 * @return resp.createSuccessJson(null, request)
	 */ 
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String create(@RequestBody String reqJson, HttpServletRequest req) {
		Request<UnionPojo> request = Request.create(reqJson, req, UnionPojo.class);
		unionService.create(request.getData());
		Response resp = new Response();
		return resp.createSuccessJson(null, request);
	}
}
