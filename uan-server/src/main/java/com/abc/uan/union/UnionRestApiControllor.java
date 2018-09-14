package com.abc.uan.union;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abc.common.AbstractController;
import com.abc.common.Request;
import com.abc.common.Response;

@RestController
@RequestMapping(value = AbstractController.REST_API_PREFIX + "/uni")
public class UnionRestApiControllor extends AbstractController {

	@Autowired
	private UnionService unionService;

	@RequestMapping(value = "/findAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String findAll(@RequestBody String reqJson, HttpServletRequest req) {
		Request request = Request.create(reqJson, req, null);
		List<UnionPojo> unionPojos = unionService.findAll();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(unionPojos, request);
	}

	@RequestMapping(value = "/findByWhere", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String findByWhere(@RequestBody String reqJson, HttpServletRequest req) {
		Request<UnionQuery> request = Request.create(reqJson, req, UnionQuery.class);
		List<UnionPojo> unionPojos = unionService.findByWhere(request.getData());
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(unionPojos, request);
	}

	@RequestMapping(value = "/loadByCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String loadByCode(@RequestBody String reqJson, HttpServletRequest req) {
		Request<String> request = Request.create(reqJson, req, String.class);
		UnionPojo unionPojo = unionService.loadByCode(request.getData());
		Response<UnionPojo> resp = new Response<UnionPojo>();
		return resp.createSuccessJson(unionPojo, request);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String create(@RequestBody String reqJson, HttpServletRequest req) {
		Request<UnionPojo> request = Request.create(reqJson, req, UnionPojo.class);
		unionService.create(request.getData());
		Response resp = new Response();
		return resp.createSuccessJson(null, request);
	}
}
