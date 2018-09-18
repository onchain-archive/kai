package com.abc.uan.catastrophe;

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
import com.abc.uan.union.UnionPojo;
import com.abc.uan.union.UnionQuery;

@RestController
@RequestMapping(value = AbstractController.REST_API_PREFIX + "/cat")
public class CatastropheRestApiControllor extends AbstractController {

	@Autowired
	private CatastropheService catastropheService;

	@RequestMapping(value = "/findAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String findAll(@RequestBody String reqJson, HttpServletRequest req) {
		Request request = Request.create(reqJson, req, null);
		List<CatastrophePojo> catastrophePojos = catastropheService.findAll();
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(catastrophePojos, request);
	}

	@RequestMapping(value = "/findByWhere", method = RequestMethod.GET)
	public String findByWhere(String name, Request<String> request, HttpServletRequest req) {
		request = Request.create(name, request, req);
		List<CatastrophePojo> catastrophePojos = catastropheService.findByWhere(request.getData());
		Response<List> resp = new Response<List>();
		return resp.createSuccessJson(catastrophePojos, request);
	}

	// @RequestMapping(value = "/findByWhere", method = RequestMethod.POST, produces
	// = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// public String findByWhere(@RequestBody String reqJson, HttpServletRequest
	// req) {
	// Request<String> request = Request.create(reqJson, req, String.class);
	// List<CatastrophePojo> catastrophePojos =
	// catastropheService.findByWhere(request.getData());
	// Response<List> resp = new Response<List>();
	// return resp.createSuccessJson(catastrophePojos, request);
	// }

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String create(@RequestBody String reqJson, HttpServletRequest req) {
		Request<CatastrophePojo> request = Request.create(reqJson, req, CatastrophePojo.class);
		catastropheService.create(request.getData());
		Response resp = new Response();
		return resp.createSuccessJson(null, request);
	}

}
